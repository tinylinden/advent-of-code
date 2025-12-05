package eu.tinylinden.aoc.y2025.d04

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 4, 2025: Printing Department ---")
internal class Day04Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "04") { printingDepartmentOne(it) },
            pOne("2025", "04") { printingDepartmentOne(it) },
            eTwo("2025", "04") { printingDepartmentTwo(it) },
            pTwo("2025", "04") { printingDepartmentTwo(it) },
        )
    }
}