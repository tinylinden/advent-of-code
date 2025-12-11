package eu.tinylinden.aoc.y2025.d11

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 11, 2025: Reactor ---")
internal class Day11Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "11") { reactorOne(it) },
            pOne("2025", "11") { reactorOne(it) },
            eTwo("2025", "11") { reactorTwo(it) },
            pTwo("2025", "11") { reactorTwo(it) },
        )
    }
}