package eu.tinylinden.aoc.y2024.d04

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 4: Ceres Search ---")
internal class Day04Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "04") { ceresSearchOne(it) },
            pOne("2024", "04") { ceresSearchOne(it) },
            eTwo("2024", "04") { ceresSearchTwo(it) },
            pTwo("2024", "04") { ceresSearchTwo(it) },
        )
    }
}