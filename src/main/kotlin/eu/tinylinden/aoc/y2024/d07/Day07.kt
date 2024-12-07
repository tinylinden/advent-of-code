package eu.tinylinden.aoc.y2024.d07

import eu.tinylinden.aoc.base.parseLists

fun bridgeRepairOne(input: String): Long =
    parse(input)
        .filter { check(it[0], it[1], it.drop(2), listOf(Add, Mul)) }
        .sumOf { it.first() }

fun bridgeRepairTwo(input: String): Long =
    parse(input)
        .filter { check(it[0], it[1], it.drop(2), listOf(Add, Mul, Con)) }
        .sumOf { it.first() }

private typealias Op = (Long, Long) -> Long

private val Add: Op = { l, r -> l + r }
private val Mul: Op = { l, r -> l * r }
private val Con: Op = { l, r -> "$l$r".toLong() }

private fun check(expected: Long, head: Long, rest: List<Long>, ops: List<Op>): Boolean =
    when {
        rest.isEmpty() -> expected == head
        expected < head -> false
        else -> ops.any { check(expected, it(head, rest[0]), rest.drop(1), ops) }
    }

private fun parse(input: String): List<List<Long>> =
    parseLists(input.replace(":", ""), Regex(".*"), ' ') { it.toLong() }
