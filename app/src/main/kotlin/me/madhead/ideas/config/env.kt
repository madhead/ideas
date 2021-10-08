package me.madhead.ideas.config

import org.koin.core.qualifier.named
import org.koin.dsl.module

const val TOKEN_GITHUB = "TOKEN_GITHUB"
const val LABEL_IDEA = "LABEL_IDEA"

val envModule = module {
    listOf(
        TOKEN_GITHUB,
        LABEL_IDEA,
    ).forEach { variable ->
        single(named(variable)) {
            System.getenv(variable)!!
        }
    }
}
