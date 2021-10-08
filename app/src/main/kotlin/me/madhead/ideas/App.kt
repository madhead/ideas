package me.madhead.ideas

import java.io.File
import java.io.PrintWriter
import kotlin.system.measureTimeMillis
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

suspend fun main(args: Array<String>) {
    startKoin {
        modules(
            envModule,
            githubModule,
            utilsModule,
            renderModule,
        )
    }

    measureTimeMillis {
        App().run(args[0])
    }.let {
        println("${args[0]} rendered in ${it}ms")
    }
}

internal class App : KoinComponent {
    private val gitHub: GitHub by inject()
    private val issuesPredicate: IssuesPredicate by inject()
    private val issuesComparator: IssuesComparator by inject()
    private val render: Markdown by inject()

    suspend fun run(file: String) {
        File(file).outputStream().use { output ->
            val printWriter = PrintWriter(output)

            javaClass.classLoader.getResourceAsStream("header.md")!!.copyTo(output)

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
                        printWriter.println(it)
                    }
            }
            printWriter.flush()

            javaClass.classLoader.getResourceAsStream("footer.md")!!.copyTo(output)
        }
    }
}
