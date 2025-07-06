package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.http.Url

open class Request(
    protected val url: Url,
    protected val headers: Map<String, List<String>>,
    protected val httpClient: HttpClient,
)
