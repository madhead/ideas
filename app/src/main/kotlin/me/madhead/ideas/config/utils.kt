package me.madhead.ideas.config

import me.madhead.ideas.utils.IssuesComparator
import me.madhead.ideas.utils.IssuesPredicate
import org.koin.core.qualifier.named
import org.koin.dsl.module

val utilsModule = module {
    single {
        IssuesPredicate(get(named(LABEL_IDEA)))
    }
    single {
        IssuesComparator()
    }
}
