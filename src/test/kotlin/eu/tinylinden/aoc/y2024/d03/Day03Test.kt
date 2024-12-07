package eu.tinylinden.aoc.y2024.d03

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 3, 2024: Mull It Over ---")
internal class Day03Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "03") { mullItOver(it, false).toLong() },
            pOne("2024", "03") { mullItOver(it, false).toLong() },
            eTwo("2024", "03") { mullItOver(it, true).toLong() },
            pTwo("2024", "03") { mullItOver(it, true).toLong() },
        )
    }
}