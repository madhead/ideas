package me.madhead.ideas.render

import me.madhead.ideas.github.model.Issue

class Markdown : (Issue) -> String {
    override fun invoke(issue: Issue): String {
        return """
            1. ${header(issue)}${details(issue) ?: ""}
        """.trimIndent()
    }

    private fun header(issue: Issue): String {
        val link = "[${issue.title}](${issue.htmlUrl})"
        val title = if (issue.state == "closed") {
            "~~$link~~"
        } else {
            link
        }
        val comments = if (issue.comments > 0) {
            " ┃ \uD83D\uDCAC × ${issue.comments}"
        } else {
            null
        }
        val reactions = if (issue.reactions.totalCount > 0) {
            " ┃ ✨ × ${issue.reactions.totalCount}"
        } else {
            null
        }

        return title + (comments ?: "") + (reactions ?: "")
    }

    private fun details(issue: Issue): String? {
        return issue.body?.let { body ->
            """
                <details>
                  <summary>Detauls:</summary>

${body.lines().joinToString(separator = "\n") { "                  > $it" }}
                </details>
            """
        }
    }
}
