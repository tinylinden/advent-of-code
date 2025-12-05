package eu.tinylinden.aoc.y2025.d05

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 5, 2025: Cafeteria ---")
internal class Day05Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "05") { cafeteriaOne(it) },
            pOne("2025", "05") { cafeteriaOne(it) },
            eTwo("2025", "05") { cafeteriaTwo(it) },
            pTwo("2025", "05") { cafeteriaTwo(it) },
        )
    }
}