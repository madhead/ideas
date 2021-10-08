package me.madhead.ideas.github.api

data class Response<D, R : Request<D>>(
    val data: D,
    val next: R? = null
)
