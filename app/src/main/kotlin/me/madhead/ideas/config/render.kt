package me.madhead.ideas.config

import me.madhead.ideas.render.Markdown
import org.koin.dsl.module

val renderModule = module {
    single {
        Markdown()
    }
}
