package me.madhead.ideas.github.model

import kotlinx.serialization.Serializable

@Serializable
data class PullRequest(
    val url: String
)
