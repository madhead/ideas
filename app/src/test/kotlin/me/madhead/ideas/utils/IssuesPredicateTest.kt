package me.madhead.ideas.utils

import kotlinx.coroutines.runBlocking
import me.madhead.ideas.github.model.Issue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IssuesPredicateTest {
    private val sut = IssuesPredicate("\uD83D\uDCA1 idea")

    @ParameterizedTest
    @MethodSource("issues")
    fun `IssuesPredicate should filter Issues according to the rules`(input: List<Issue>, expected: List<Issue>) {
        assertEquals(expected, input.filter { runBlocking { sut(it) } })
    }

    private fun issues(): List<Arguments> = arguments(this.javaClass)
}
