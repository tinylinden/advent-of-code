package eu.tinylinden.aoc.y2024.d13

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 13, 2024: Claw Contraption ---")
internal class Day13Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "13") { clawContraptionOne(it) },
            pOne("2024", "13") { clawContraptionOne(it) },
            eTwo("2024", "13") { clawContraptionTwo(it) },
            pTwo("2024", "13") { clawContraptionTwo(it) },
        )
    }
}