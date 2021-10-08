package me.madhead.ideas.utils

import me.madhead.ideas.github.model.Issue

class IssuesPredicate(private val markerLabel: String) : suspend (Issue) -> Boolean {
    override suspend fun invoke(issue: Issue): Boolean = issue.labels.any { it.name == markerLabel } && issue.pullRequest == null
}
