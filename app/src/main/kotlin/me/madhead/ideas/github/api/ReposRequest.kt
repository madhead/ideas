package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.appendPathSegments

class ReposRequest(
    url: Url,
    headers: Map<String, List<String>>,
    httpClient: HttpClient,
) : Request(url, headers, httpClient) {
    val issues: IssuesRequest
        get() = IssuesRequest(
            URLBuilder(url).appendPathSegments("issues").build(),
            headers,
            httpClient,
        )
}
