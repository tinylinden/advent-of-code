package eu.tinylinden.aoc.y2025.d12

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@Disabled
@DisplayName("--- Day 12, 2025: Christmas Tree Farm ---")
internal class Day12Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "12") { xmasTreeFarmOne(it) },
            pOne("2025", "12") { xmasTreeFarmOne(it) },
            eTwo("2025", "12") { xmasTreeFarmTwo(it) },
            pTwo("2025", "12") { xmasTreeFarmTwo(it) },
        )
    }
}