package eu.tinylinden.aoc.y2024.d02

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@DisplayName("--- Day 2: Red-Nosed Reports ---")
internal class Day02Test {

    @Nested
    @DisplayName("--- Part One ---")
    inner class PartOne {

        @Test
        @DisplayName("example")
        fun `test example`() {
            expectThat(countSafe(example(), false)).isEqualTo(2)
        }

        @Disabled
        @Test
        @DisplayName("puzzle")
        fun `test puzzle`() {
            expectThat(countSafe(puzzle(), false)).isEqualTo(252)
        }
    }

    @Nested
    @DisplayName("--- Part Two ---")
    inner class PartTwo {

        @Test
        @DisplayName("example")
        fun `test example`() {
            expectThat(countSafe(example(), true)).isEqualTo(4)
        }

        @Disabled
        @Test
        @DisplayName("puzzle")
        fun `test puzzle`() {
            expectThat(countSafe(puzzle(), true)).isEqualTo(324)
        }
    }

    private fun example() = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    private fun puzzle() = ""
}
