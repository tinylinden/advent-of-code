package eu.tinylinden.aoc.y2024.d02

import eu.tinylinden.aoc.*
import eu.tinylinden.aoc.Tested
import eu.tinylinden.aoc.exampleOne
import eu.tinylinden.aoc.exampleTwo
import eu.tinylinden.aoc.puzzleOne
import eu.tinylinden.aoc.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 2, 2024: Red-Nosed Reports ---")
internal class Day02Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            exampleOne("2024", "02") { redNosedReports(it, false) },
            puzzleOne("2024", "02") { redNosedReports(it, false) },
            exampleTwo("2024", "02") { redNosedReports(it, true) },
            puzzleTwo("2024", "02") { redNosedReports(it, true) },
        )
    }
}
