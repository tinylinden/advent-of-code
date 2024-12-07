package eu.tinylinden.aoc.y2024.d06

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 6, 2024: Guard Gallivant ---")
internal class Day06Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "06") { guardGallivantOne(it).toLong() },
            pOne("2024", "06") { guardGallivantOne(it).toLong() },
            eTwo("2024", "06") { guardGallivantTwo(it).toLong() },
            pTwo("2024", "06") { guardGallivantTwo(it).toLong() },
        )
    }
}