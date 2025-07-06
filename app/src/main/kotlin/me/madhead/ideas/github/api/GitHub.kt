package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.http.buildUrl
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GitHub(
    private val token: String
) : AutoCloseable {
    private val httpClient = HttpClient(Java) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(Auth) {
            providers.add(GitHubAuthProvider(token))
        }
    }

    fun repos(owner: String, repo: String): ReposRequest {
        return ReposRequest(
            buildUrl {
                protocol = URLProtocol.HTTPS
                host = "api.github.com"
                encodedPath = "repos/$owner/$repo"
            },
            emptyMap(),
            httpClient,
        )
    }

    override fun close() {
        httpClient.close()
    }
}
