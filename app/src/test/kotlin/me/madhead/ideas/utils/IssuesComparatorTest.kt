package me.madhead.ideas.utils

import me.madhead.ideas.github.model.Issue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IssuesComparatorTest {
    private val sut = IssuesComparator()

    @ParameterizedTest
    @MethodSource("issues")
    fun `IssuesComparator should sort Issues according to the rules`(input: List<Issue>, expected: List<Issue>) {
        assertEquals(expected, input.sortedWith(sut))
    }

    private fun issues(): List<Arguments> = arguments(this.javaClass)
}
