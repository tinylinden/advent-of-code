package eu.tinylinden.aoc.y2024.d05

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 5, 2024: Print Queue ---")
internal class Day05Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "05") { printQueueOne(it) },
            pOne("2024", "05") { printQueueOne(it) },
            eTwo("2024", "05") { printQueueTwo(it) },
            pTwo("2024", "05") { printQueueTwo(it) },
        )
    }
}