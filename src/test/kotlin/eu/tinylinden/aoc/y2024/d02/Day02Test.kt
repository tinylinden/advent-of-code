package eu.tinylinden.aoc.y2024.d02

import eu.tinylinden.aoc.Tested
import eu.tinylinden.aoc.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.argumentSet
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 2: Red-Nosed Reports ---")
internal class Day02Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            argumentSet("Part One - example", "y2024d02p1-example", Tested { countSafe(it, false) }),
            argumentSet("Part One - puzzle", "y2024d02p1-puzzle", Tested { countSafe(it, false) }),
            argumentSet("Part Two - example", "y2024d02p2-example", Tested { countSafe(it, true) }),
            argumentSet("Part Two - puzzle", "y2024d02p2-puzzle", Tested { countSafe(it, true) }),
        )
    }
}
