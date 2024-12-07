package eu.tinylinden.aoc.y2024.d07

import eu.tinylinden.aoc.base.parseLists
import eu.tinylinden.aoc.base.prepend
import eu.tinylinden.aoc.base.rest
import kotlin.math.pow

fun bridgeRepairOne(input: String): Int {
    fun possible(expected: Long, items: Items): Boolean =
        operations(items.size - 1)
            .any { expected == eval(items, it) }

    return parse(input)
        .filter { possible(it.first(), it.rest()) }
        .sumOf { it.first() }
        .toInt()
}

fun bridgeRepairTwo(input: String): Int =
    0

private fun eval(items: Items, ops: Operations): Long {
    val part = if (ops.first() == '0') { // 0 -> +
        items[0] + items[1]
    } else { // 1 -> *
        items[0] * items[1]
    }

    return if (items.size == 2) {
        part
    } else {
        eval(items.drop(2).prepend(part), ops.drop(1))
    }
}

private fun operations(n: Int): Sequence<Operations> =
    generateSequence(0) { it + 1 }
        .map { it.toString(2).padStart(n, '0') }
        .take(2.0.pow(n).toInt())

private fun parse(input: String): List<List<Long>> =
    parseLists(input.replace(":", ""), Regex(".*"), ' ') { it.toLong() }

private typealias Items = List<Long>
private typealias Operations = String
