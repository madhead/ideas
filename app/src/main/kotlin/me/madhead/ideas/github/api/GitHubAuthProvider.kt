package me.madhead.ideas.github.api

import io.ktor.client.plugins.auth.AuthProvider
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.auth.HttpAuthHeader

class GitHubAuthProvider(private val token: String) : AuthProvider {
    @Deprecated("Please use sendWithoutRequest function instead", level = DeprecationLevel.ERROR)
    override val sendWithoutRequest = false

    override fun isApplicable(auth: HttpAuthHeader) = true

    override suspend fun addRequestHeaders(
        request: HttpRequestBuilder,
        authHeader: HttpAuthHeader?
    ) {
        request.headers {
            if (contains(HttpHeaders.Authorization)) {
                remove(HttpHeaders.Authorization)
            }
            append(HttpHeaders.Authorization, "token $token")
        }
    }
}
