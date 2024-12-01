package eu.tinylinden.aoc.y2024.d02

import kotlin.math.abs

// https://adventofcode.com/2024/day/2

fun countSafe(s: String, dampener: Boolean): Int =
    parse(s).count { report ->
        isSafe(report) || dampener && dampened(report).any { isSafe(it) }
    }

private fun dampened(report: List<Int>): Sequence<List<Int>> =
    report.indices.asSequence()
        .map {
            val tmp = report.toMutableList()
            tmp.removeAt(it)
            tmp
        }

private fun isSafe(report: List<Int>): Boolean =
    (isIncreasing(report) || isDecreasing(report)) && report.zipWithNext().all { (l, r) -> abs(l - r) <= 3 }

private fun isIncreasing(report: List<Int>): Boolean =
    report.zipWithNext().all { (l, r) -> l < r }

private fun isDecreasing(report: List<Int>): Boolean =
    report.zipWithNext().all { (l, r) -> l > r }

private fun parse(s: String): List<List<Int>> =
    s.lines().map { line ->
        line.split(" ").map { it.toInt() }
    }