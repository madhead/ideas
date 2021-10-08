package me.madhead.ideas.github.api

import io.ktor.http.URLBuilder
import io.ktor.http.Url

data class Link(
    val url: Url,
    val rel: String
) {
    companion object {
        fun of(value: String): Link? {
            val matches = Regex("""<(.+?)>;\s*rel="(.+?)"""").matchEntire(value) ?: return null
            val (_, urlString, rel) = (matches.groupValues)

            return Link(URLBuilder(urlString).build(), rel)
        }
    }

    val isNext = rel.equals("next", ignoreCase = true)
}
