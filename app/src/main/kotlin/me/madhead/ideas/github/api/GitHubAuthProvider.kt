package me.madhead.ideas.github.api

import io.ktor.client.features.auth.AuthProvider
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.util.InternalAPI

class GitHubAuthProvider(private val token: String) : AuthProvider {
    override val sendWithoutRequest: Boolean
        get() = error("Deprecated")

    override fun sendWithoutRequest(request: HttpRequestBuilder) = false

    @OptIn(InternalAPI::class)
    override suspend fun addRequestHeaders(request: HttpRequestBuilder) {
        request.headers {
            if (contains(HttpHeaders.Authorization)) {
                remove(HttpHeaders.Authorization)
            }
            append(HttpHeaders.Authorization, "token $token")
        }
    }

    override fun isApplicable(auth: HttpAuthHeader) = true
}
