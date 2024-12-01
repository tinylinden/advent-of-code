package eu.tinylinden.aoc.y2024.d01

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@DisplayName("--- Day 1: Historian Hysteria ---")
internal class Day01Test {

    @Nested
    @DisplayName("--- Part One ---")
    inner class PartOne {

        @Test
        @DisplayName("example")
        fun `test example`() {
            expectThat(distance(example())).isEqualTo(11)
        }

        @Disabled
        @Test
        @DisplayName("puzzle")
        fun `test puzzle`() {
            expectThat(distance(puzzle())).isEqualTo(2430334)
        }
    }


    @Nested
    @DisplayName("--- Part Two ---")
    inner class PartTwo {

        @Test
        @DisplayName("example")
        fun `test example`() {
            expectThat(similarity(example())).isEqualTo(31)
        }

        @Disabled
        @Test
        @DisplayName("puzzle")
        fun `test puzzle`() {
            expectThat(similarity(puzzle())).isEqualTo(28786472)
        }
    }

    private fun example() = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    private fun puzzle() = ""
}
