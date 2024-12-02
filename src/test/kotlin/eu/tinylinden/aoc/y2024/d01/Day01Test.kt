package eu.tinylinden.aoc.y2024.d01

import eu.tinylinden.aoc.Tested
import eu.tinylinden.aoc.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.argumentSet
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 1: Historian Hysteria ---")
internal class Day01Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            argumentSet("Part One - example", "y2024d01p1-example", Tested { distance(it) }),
            argumentSet("Part One - puzzle", "y2024d01p1-puzzle", Tested { distance(it) }),
            argumentSet("Part Two - example", "y2024d01p2-example", Tested { similarity(it) }),
            argumentSet("Part Two - puzzle", "y2024d01p2-puzzle", Tested { similarity(it) }),
        )
    }
}
