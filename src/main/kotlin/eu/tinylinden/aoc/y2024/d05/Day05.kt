package eu.tinylinden.aoc.y2024.d05

import eu.tinylinden.aoc.base.parseLists
import eu.tinylinden.aoc.base.parsePairs
import eu.tinylinden.aoc.base.rest

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

private fun fixOrder(pages: Pages, rules: Rules): Pages =
    pages.sortedWith { l, r ->
        if (rules.contains(l to r)) -1 else 0
    }

private fun isInOrder(pages: Pages, rules: Rules): Boolean {
    fun options(page: Int, rest: Pages): Set<Option> =
        rest.map { page to it }.toSet()

    fun check(page: Int, rest: Pages): Boolean =
        when {
            rest.isEmpty() -> true
            (rules intersect options(page, rest)).isNotEmpty() -> false
            else -> check(rest.first(), rest.rest())
        }

    return check(pages.first(), pages.rest())
}

private fun rules(input: String): Rules =
    parsePairs(input, Regex("\\d+\\|\\d+"), '|') { it.toInt() }
        .map { (l, r) -> r to l }
        .toSet()

private fun updates(input: String): Updates =
    parseLists(input, Regex("[\\d,]+"), ',') { it.toInt() }

private typealias Pages = List<Int>
private typealias Updates = List<Pages>
private typealias Option = Pair<Int, Int>
private typealias Rules = Set<Option> // illegal options
