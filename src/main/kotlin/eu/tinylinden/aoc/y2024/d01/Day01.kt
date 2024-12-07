package eu.tinylinden.aoc.y2024.d01

import kotlin.math.abs

// https://adventofcode.com/2024/day/1

fun distance(s: String): Long =
    (left(s) zip right(s)).sumOf { (l, r) -> abs(l - r) }.toLong()

fun similarity(s: String): Long =
    similarity(left(s), right(s).groupingBy { it }.eachCount()).toLong()

private fun similarity(left: List<Int>, right: Map<Int, Int>): Int =
    left.sumOf { it * (right[it] ?: 0) }

private fun left(s: String): List<Int> =
    parse(s) { first() }

private fun right(s: String): List<Int> =
    parse(s) { last() }

private fun parse(s: String, f: List<String>.() -> String): List<Int> =
    s.lines().map { it.split("   ").f().toInt() }.sorted()
