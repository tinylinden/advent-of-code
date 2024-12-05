package eu.tinylinden.aoc.y2024.d05

import eu.tinylinden.aoc.base.parseLists
import eu.tinylinden.aoc.base.parsePairs

// https://adventofcode.com/2024/day/5

fun printQueueOne(input: String): Int =
    rules(input).let { rules ->
        updates(input)
            .filter { isInOrder(it, rules) }
            .sumOf { it[it.size / 2] }
    }

fun printQueueTwo(input: String): Int =
    rules(input).let { rules ->
        updates(input)
            .filterNot { isInOrder(it, rules) }
            .map { fixOrder(it, rules) }
            .sumOf { it[it.size / 2] }
    }

private fun fixOrder(update: List<Int>, rules: Rules): List<Int> =
    update.sortedWith { l, r ->
        when (rules[r]?.contains(l)) {
            true -> -1
            else -> 0
        }
    }

private fun isInOrder(pages: List<Int>, rules: Rules): Boolean {
    fun check(page: Int, rest: List<Int>): Boolean = when {
        rest.isEmpty() -> true
        rules[page]?.intersect(rest)?.isNotEmpty() == true -> false
        else -> check(rest.first(), rest.drop(1))
    }

    return check(pages.first(), pages.drop(1))
}

private fun rules(input: String): Rules =
    parsePairs(input, Regex("\\d+\\|\\d+"), '|') { it.toInt() }
        .groupBy({ it.second }, { it.first })

private fun updates(input: String): Updates =
    parseLists(input, Regex("[\\d,]+"), ',') { it.toInt() }

private typealias Updates = List<List<Int>>
private typealias Rules = Map<Int, List<Int>> // page -> after all of
