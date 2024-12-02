package eu.tinylinden.aoc.y2024.d02

import kotlin.math.abs

// https://adventofcode.com/2024/day/2

fun countSafe(s: String, damp: Boolean): Int =
    parse(s).count { report ->
        isSafe(report) || (damp && dampened(report).any { isSafe(it) })
    }

private fun dampened(report: List<Int>): Sequence<List<Int>> =
    report.indices.asSequence()
        .map { report.filterIndexed { i, _ -> i != it } }

private fun isSafe(report: List<Int>): Boolean =
    report.zipWithNext().let {
        (it.all(Increasing) || it.all(Decreasing)) && it.all(MaxDiffer)
    }

private val Increasing: (Pair<Int, Int>) -> Boolean = { (l, r) -> l < r }
private val Decreasing: (Pair<Int, Int>) -> Boolean = { (l, r) -> l > r }
private val MaxDiffer: (Pair<Int, Int>) -> Boolean = { (l, r) -> abs(l - r) <= 3 }

private fun parse(s: String): List<List<Int>> =
    s.lines().map { line ->
        line.split(" ").map { it.toInt() }
    }