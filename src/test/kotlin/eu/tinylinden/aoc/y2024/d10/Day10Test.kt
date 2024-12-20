package eu.tinylinden.aoc.y2024.d10

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 10, 2024: Hoof It ---")
internal class Day10Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "10") { hoofItOne(it) },
            pOne("2024", "10") { hoofItOne(it) },
            eTwo("2024", "10") { hoofItTwo(it) },
            pTwo("2024", "10") { hoofItTwo(it) },
        )
    }
}