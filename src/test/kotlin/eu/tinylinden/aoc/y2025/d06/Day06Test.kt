package eu.tinylinden.aoc.y2025.d06

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 6, 2025: Trash Compactor ---")
internal class Day06Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "06") { trashCompactorOne(it) },
            pOne("2025", "06") { trashCompactorOne(it) },
            eTwo("2025", "06") { trashCompactorTwo(it) },
            pTwo("2025", "06") { trashCompactorTwo(it) },
        )
    }
}