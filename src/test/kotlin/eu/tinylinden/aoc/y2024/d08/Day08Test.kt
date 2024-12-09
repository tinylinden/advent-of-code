package eu.tinylinden.aoc.y2024.d08

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 8, 2024: Resonant Collinearity ---")
internal class Day08Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "08") { resonantCollinearityOne(it).toLong() },
            pOne("2024", "08") { resonantCollinearityOne(it).toLong() },
            eTwo("2024", "08") { resonantCollinearityTwo(it).toLong() },
            pTwo("2024", "08") { resonantCollinearityTwo(it).toLong() },
        )
    }
}