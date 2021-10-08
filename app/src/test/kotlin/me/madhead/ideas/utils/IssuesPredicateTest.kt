package me.madhead.ideas.utils

import java.util.stream.Stream
import kotlinx.coroutines.runBlocking
import me.madhead.ideas.github.model.Issue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IssuesPredicateTest {
    private val sut = IssuesPredicate("\uD83D\uDCA1 idea")

    @ParameterizedTest(name = "{0}")
    @MethodSource("issues")
    fun `IssuesPredicate should filter Issues according to the rules`(input: List<Issue>, expected: List<Issue>) {
        Assertions.assertEquals(expected, input.filter { runBlocking { sut(it) } })
    }

    private fun issues(): Stream<Arguments> = arguments(this.javaClass)
}

