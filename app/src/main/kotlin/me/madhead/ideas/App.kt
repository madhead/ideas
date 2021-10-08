package me.madhead.ideas

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import me.madhead.ideas.config.envModule
import me.madhead.ideas.config.githubModule
import me.madhead.ideas.config.renderModule
import me.madhead.ideas.config.utilsModule
import me.madhead.ideas.github.api.GitHub
import me.madhead.ideas.render.Markdown
import me.madhead.ideas.utils.IssuesComparator
import me.madhead.ideas.utils.IssuesPredicate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

suspend fun main() {
    startKoin {
        modules(
            envModule,
            githubModule,
            utilsModule,
            renderModule,
        )
    }

    App().run()
}

internal class App : KoinComponent {
    private val gitHub: GitHub by inject()
    private val issuesPredicate: IssuesPredicate by inject()
    private val issuesComparator: IssuesComparator by inject()
    private val render: Markdown by inject()

    suspend fun run() {
        javaClass.classLoader.getResourceAsStream("header.md").copyTo(System.out)

        gitHub.use { gitHub ->
            gitHub
                .repos("madhead", "ideas")
                .issues()
                .filter(issuesPredicate)
                .toList()
                .sortedWith(issuesComparator)
                .map {
                    coroutineScope {
                        async {
                            render(it)
                        }
                    }
                }
                .map { it.await() }
                .forEach {
                    println(it)
                }
        }

        javaClass.classLoader.getResourceAsStream("footer.md").copyTo(System.out)
    }
}
