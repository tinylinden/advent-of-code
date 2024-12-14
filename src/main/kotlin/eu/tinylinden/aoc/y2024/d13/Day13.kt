package eu.tinylinden.aoc.y2024.d13

import eu.tinylinden.aoc.base.parsePairs

fun clawContraptionOne(input: String): Long =
    clawMachines(input).minTokens()

fun clawContraptionTwo(input: String): Long =
    clawMachines(input, 10_000_000_000_000).minTokens()

private fun List<ClawMachine>.minTokens(): Long =
    map { (a, b, prize) -> Triple(det(a, b), det(prize, b), det(a, prize)) }
        .sumOf { (d, da, db) ->
            val a = da / d
            val b = db / d

            when {
                a < 0 || b < 0 -> 0               // negatives impossible
                da % d != 0L || db % d != 0L -> 0 // fractions impossible
                else -> 3 * a + b
            }
        }

// https://pl.wikipedia.org/wiki/Wyznacznik#Obliczanie_wyznacznik%C3%B3w
private fun det(left: Pair<Long, Long>, right: Pair<Long, Long>): Long =
    left.first * right.second - right.first * left.second

private typealias Button = Pair<Long, Long>
private typealias Prize = Pair<Long, Long>
private typealias ClawMachine = Triple<Button, Button, Prize>

// ugly, but working
private fun clawMachines(input: String, adj: Long = 0): List<ClawMachine> =
    parsePairs(input, Regex("\\d+")) { it.toLong() }
        .chunked(3) { ClawMachine(it[0], it[1], it[2].let { (a, b) -> a + adj to b + adj }) }
