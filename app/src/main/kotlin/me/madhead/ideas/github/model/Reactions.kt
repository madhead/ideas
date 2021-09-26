package me.madhead.ideas.github.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reactions(
    @SerialName("total_count")
    val totalCount: Int,
)
