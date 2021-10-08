package me.madhead.ideas.github.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    @SerialName("html_url")
    val htmlUrl: String,
    val number: Int,
    val title: String,
    val labels: List<Label>,
    val state: String,
    val assignee: Assignee? = null,
    val assignees: List<Assignee> = emptyList(),
    val comments: Int,
    @SerialName("pull_request")
    val pullRequest: PullRequest? = null,
    val body: String? = null,
    val reactions: Reactions,
)
