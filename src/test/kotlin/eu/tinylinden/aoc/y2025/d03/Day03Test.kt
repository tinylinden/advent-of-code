package eu.tinylinden.aoc.y2025.d03

import eu.tinylinden.aoc.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("--- Day 3, 2025: Lobby ---")
internal class Day03Test {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("2025", "03") { lobbyOne(it) },
            pOne("2025", "03") { lobbyOne(it) },
            eTwo("2025", "03") { lobbyTwo(it) },
            pTwo("2025", "03") { lobbyTwo(it) },
        )
    }
}