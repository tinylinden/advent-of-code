package eu.tinylinden.aoc.y2024.d07

import eu.tinylinden.aoc.base.parseLists
import eu.tinylinden.aoc.base.prepend
import eu.tinylinden.aoc.base.rest

fun bridgeRepairOne(input: String): Long =
    parse(input)
        .filter { check(it.first(), it.rest(), listOf(Add, Mul)) }
        .sumOf { it.first() }

fun bridgeRepairTwo(input: String): Long =
    parse(input)
        .filter { check(it.first(), it.rest(), listOf(Add, Mul, Con)) }
        .sumOf { it.first() }

private typealias Op = (Long, Long) -> Long

private val Add: Op = { l, r -> l + r }
private val Mul: Op = { l, r -> l * r }
private val Con: Op = { l, r -> "$l$r".toLong() }

private fun check(expected: Long, items: List<Long>, ops: List<Op>): Boolean =
    when {
        items.size == 1 -> expected == items[0]
        expected < items[0] -> false
        else -> ops.any { check(expected, items.drop(2).prepend(it(items[0], items[1])), ops) }
    }

private fun parse(input: String): List<List<Long>> =
    parseLists(input.replace(":", ""), Regex(".*"), ' ') { it.toLong() }
