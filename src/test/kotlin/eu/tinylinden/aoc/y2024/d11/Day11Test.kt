package eu.tinylinden.aoc.y2024.d11

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 11, 2024: Plutonian Pebbles ---")
internal class Day11Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "11") { plutonianPebblesOne(it) },
            pOne("2024", "11") { plutonianPebblesOne(it) },
            eTwo("2024", "11") { plutonianPebblesTwo(it) },
            pTwo("2024", "11") { plutonianPebblesTwo(it) },
        )
    }
}