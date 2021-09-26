package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.http.URLBuilder
import io.ktor.http.clone
import io.ktor.http.pathComponents

class ReposRequest(
    url: URLBuilder,
    headers: Map<String, List<String>>,
    httpClient: HttpClient,
) : Request<Nothing>(url, headers, httpClient) {
    val issues: IssuesRequest
        get() = IssuesRequest(
            url.clone().pathComponents("issues"),
            headers,
            httpClient,
        )
}
