package me.madhead.ideas.config

import me.madhead.ideas.github.api.GitHub
import org.koin.core.qualifier.named
import org.koin.dsl.module

val githubModule = module {
    single {
        GitHub(get(named(TOKEN_GITHUB)))
    }
}
