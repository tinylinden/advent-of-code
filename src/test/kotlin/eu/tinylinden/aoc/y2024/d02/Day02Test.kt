package eu.tinylinden.aoc.y2024.d02

import eu.tinylinden.aoc.*
import eu.tinylinden.aoc.Tested
import eu.tinylinden.aoc.eOne
import eu.tinylinden.aoc.eTwo
import eu.tinylinden.aoc.pOne
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
            eOne("2024", "02") { redNosedReports(it, false) },
            pOne("2024", "02") { redNosedReports(it, false) },
            eTwo("2024", "02") { redNosedReports(it, true) },
            pTwo("2024", "02") { redNosedReports(it, true) },
        )
    }
}
