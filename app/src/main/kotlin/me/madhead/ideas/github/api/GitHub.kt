package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

class GitHub(
    private val token: String
) : AutoCloseable {
    private val httpClient = HttpClient(Java) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Auth) {
            providers.add(GitHubAuthProvider(token))
        }
    }

    fun repos(owner: String, repo: String): ReposRequest {
        return ReposRequest(
            URLBuilder(
                protocol = URLProtocol.HTTPS,
                host = "api.github.com",
                encodedPath = "repos/$owner/$repo"
            ),
            emptyMap(),
            httpClient,
        )
    }

    override fun close() {
        httpClient.close()
    }
}
