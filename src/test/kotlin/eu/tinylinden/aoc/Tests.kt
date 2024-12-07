package eu.tinylinden.aoc

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.params.provider.Arguments.argumentSet
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.nio.charset.StandardCharsets

internal fun interface Tested : (String) -> Long

internal typealias TestCase = Pair<String, Long>

internal fun runTest(case: String, tested: Tested) {
    testCase(case).let { (given, expected) ->
        expectThat(tested(given)).isEqualTo(expected)
    }
}

internal fun eOne(y: String, d: String, tested: Tested) =
    argumentSet("Part One - example", "y${y}d${d}p1-example", tested)

internal fun pOne(y: String, d: String, tested: Tested) =
    argumentSet("Part One - puzzle", "y${y}d${d}p1-puzzle", tested)

internal fun eTwo(y: String, d: String, tested: Tested) =
    argumentSet("Part Two - example", "y${y}d${d}p2-example", tested)

internal fun pTwo(y: String, d: String, tested: Tested) =
    argumentSet("Part Two - puzzle", "y${y}d${d}p2-puzzle", tested)

private fun testCase(case: String): TestCase =
    try {
        object {}.javaClass.getResource("/private/$case")
            ?.readText(StandardCharsets.UTF_8)
            ?.let { it.given() to it.expected() }
            ?: throw IllegalArgumentException()
    } catch (ex: Exception) {
        assumeTrue(false)
        "" to 0 // irrelevant - failed assumption above will skip test
    }

private fun String.given(): String = substringAfter("\n").trimEnd()

private fun String.expected(): Long = substringBefore("\n").toLong()
