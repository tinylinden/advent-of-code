package eu.tinylinden.aoc.y2025.d10

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@Disabled
@DisplayName("--- Day 10, 2025: Factory---")
internal class Day10Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "10") { factoryOne(it) },
            pOne("2025", "10") { factoryOne(it) },
            eTwo("2025", "10") { factoryTwo(it) },
            pTwo("2025", "10") { factoryTwo(it) },
        )
    }
}