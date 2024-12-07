package eu.tinylinden.aoc.y2024.d07

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 7, 2024: Bridge Repair ---")
internal class Day07Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2024", "07") { bridgeRepairOne(it) },
            pOne("2024", "07") { bridgeRepairOne(it) },
            eTwo("2024", "07") { bridgeRepairTwo(it) },
            pTwo("2024", "07") { bridgeRepairTwo(it) },
        )
    }
}