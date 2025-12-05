package eu.tinylinden.aoc.y2025.d02

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 2, 2025: Gift Shop ---")
internal class Day02Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "02") { giftShopOne(it) },
            pOne("2025", "02") { giftShopOne(it) },
            eTwo("2025", "02") { giftShopTwo(it) },
            pTwo("2025", "02") { giftShopTwo(it) },
        )
    }
}