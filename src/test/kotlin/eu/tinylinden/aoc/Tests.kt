package eu.tinylinden.aoc

import org.junit.jupiter.api.Assumptions.assumeTrue
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.nio.charset.StandardCharsets

internal fun interface Tested : (String) -> Int

internal typealias TestCase = Pair<String, Int>

internal fun runTest(case: String, tested: Tested) {
    testCase(case).let { (given, expected) ->
        expectThat(tested(given)).isEqualTo(expected)
    }
}

private fun testCase(case: String): TestCase =
    try {
        object {}.javaClass.getResource("/private/$case")
            ?.readText(StandardCharsets.UTF_8)
            ?.let { it.given() to it.expected() }
            ?: throw IllegalStateException()
    } catch (ex: Exception) {
        assumeTrue(false)
        "" to 0 // irrelevant - failed assumption above will skip test
    }

private fun String.given(): String = substringAfter("\n")

private fun String.expected(): Int = substringBefore("\n").toInt()
