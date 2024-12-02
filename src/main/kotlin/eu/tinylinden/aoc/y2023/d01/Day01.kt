package eu.tinylinden.aoc.y2023.d01

// https://adventofcode.com/2023/day/1

fun calibration(input: String, norm: Boolean): Int =
    if (norm) {
        parse(input) { norm(it) }.sum()
    } else {
        parse(input) { it }.sum()
    }

private fun norm(line: String): String =
    dict.foldIndexed(line) { i, acc, v -> acc.replace(v, "$v$i$v") }

private fun parse(input: String, norm: (String) -> String): List<Int> =
    input.lines()
        .map { norm(it) }
        .map { it.replace(Regex("\\D"), "") }
        .map { "${it.first()}${it.last()}".toInt() }

private val dict =
    listOf(" ", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
