package eu.tinylinden.aoc.y2024.d01

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 1, 2024: Historian Hysteria ---")
internal class Day01Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "01") { distance(it) },
            pOne("2024", "01") { distance(it) },
            eTwo("2024", "01") { similarity(it) },
            pTwo("2024", "01") { similarity(it) },
        )
    }
}
