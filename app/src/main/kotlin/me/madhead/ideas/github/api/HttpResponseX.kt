package me.madhead.ideas.github.api

import io.ktor.client.statement.HttpResponse
import io.ktor.http.Url

val HttpResponse.next: Url?
    get() {
        return this
            .headers["Link"]
            ?.split(",")
            ?.map { it.trim() }
            ?.mapNotNull { Link.of(it) }
            ?.firstOrNull { it.isNext }
            ?.url
    }
