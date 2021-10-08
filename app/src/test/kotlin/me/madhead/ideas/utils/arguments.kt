package me.madhead.ideas.utils

import java.io.File
import java.net.URL
import java.util.stream.Stream
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.madhead.ideas.github.model.Issue
import org.junit.jupiter.api.Named
import org.junit.jupiter.params.provider.Arguments

fun arguments(testClass: Class<*>): Stream<Arguments> {
    val url: URL = testClass.getResource(testClass.simpleName)!!
    val files: Array<File> = File(url.toURI()).listFiles()!!

    return files
        .map { test ->
            val testName = File(test.nameWithoutExtension).nameWithoutExtension
            val testType = File(test.nameWithoutExtension).extension

            Triple(testName, testType, test)
        }
        .groupBy({ it.first }, { it.second to it.third })
        .mapValues { (testName, testFiles) ->
            testFiles.toMap()
        }
        .map { (testName, testFiles) ->
            Arguments.of(
                Named.of(
                    testName,
                    Json.decodeFromString<List<Issue>>(testFiles["input"]!!.readText()),
                ),
                Json.decodeFromString<List<Issue>>(testFiles["expected"]!!.readText()),
            )
        }
        .stream()
}