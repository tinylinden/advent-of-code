package eu.tinylinden.aoc.y2024.d03

import eu.tinylinden.aoc.Tested
import eu.tinylinden.aoc.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.argumentSet
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 3: Mull It Over ---")
internal class Day03KtTest {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            argumentSet("Part One - example", "y2024d03p1-example", Tested { mullItOver(it, false) }),
            argumentSet("Part One - puzzle", "y2024d03p1-puzzle", Tested { mullItOver(it, false) }),
            argumentSet("Part Two - example", "y2024d03p2-example", Tested { mullItOver(it, true) }),
            argumentSet("Part Two - puzzle", "y2024d03p2-puzzle", Tested { mullItOver(it, true) }),
        )
    }
}