package eu.tinylinden.aoc.y2025.d07

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 7, 2025: Laboratories ---")
internal class Day07Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "07") { laboratoriesOne(it) },
            pOne("2025", "07") { laboratoriesOne(it) },
            eTwo("2025", "07") { laboratoriesTwo(it) },
            pTwo("2025", "07") { laboratoriesTwo(it) },
        )
    }
}