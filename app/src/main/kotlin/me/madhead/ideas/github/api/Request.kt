package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.http.URLBuilder

open class Request<T>(
    protected val url: URLBuilder,
    protected val headers: Map<String, List<String>>,
    protected val httpClient: HttpClient,
)
