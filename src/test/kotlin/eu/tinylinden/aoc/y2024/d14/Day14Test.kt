package eu.tinylinden.aoc.y2024.d14

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 14, 2024: Restroom Redoubt ---")
internal class Day14Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "14") { restroomRedoubtOne(it) },
            pOne("2024", "14") { restroomRedoubtOne(it) },
            eTwo("2024", "14") { restroomRedoubtTwo(it) },
            pTwo("2024", "14") { restroomRedoubtTwo(it) },
        )
    }
}