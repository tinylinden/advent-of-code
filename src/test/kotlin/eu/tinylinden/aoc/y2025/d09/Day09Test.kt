package eu.tinylinden.aoc.y2025.d09

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 9, 2025: Movie Theater ---")
internal class Day09Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "09") { movieTheaterOne(it) },
            pOne("2025", "09") { movieTheaterOne(it) },
            eTwo("2025", "09") { movieTheaterTwo(it) },
            pTwo("2025", "09") { movieTheaterTwo(it) },
        )
    }
}