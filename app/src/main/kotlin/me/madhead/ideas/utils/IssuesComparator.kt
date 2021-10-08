package me.madhead.ideas.utils

import me.madhead.ideas.github.model.Issue

class IssuesComparator : Comparator<Issue> by
compareBy<Issue>({
    // Undone first
    when (it.state) {
        "open" -> 1
        "closed" -> 3
        else -> 2
    }
}).thenBy({
    // In-progress first
    when {
        it.assignee != null || it.assignees.isNotEmpty() -> 1
        else -> 2
    }
}).thenByDescending({
    // Most comments first
    it.comments
}).thenByDescending({
    // Most reactions first
    it.reactions.totalCount
}).thenBy({
    // Alphabetically
    it.title
}).thenBy({
    // Finally, when all other fields are equal (almost impossible), sort by internal IDs
    it.number
})
