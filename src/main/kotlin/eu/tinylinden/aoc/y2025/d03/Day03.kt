package eu.tinylinden.aoc.y2025.d03

fun lobbyOne(input: String): Long = solve(input, 2)

fun lobbyTwo(input: String): Long = solve(input, 12)

private typealias Battery = Int
private typealias BatteryBank = List<Battery>

private fun solve(input: String, size: Int): Long =
    parse(input)
        .map { bank -> joltage(bank, size, emptyList()) }
        .sumOf { it.joinToLong() }

private tailrec fun joltage(bank: BatteryBank, size: Int, acc: List<Int>): List<Int> =
    if (size == 0) {
        acc
    } else {
        val (idx, vol) = bank.dropLast(size - 1) // keep enough batteries for later
            .mapIndexed { i, v -> i to v } // index of max vol will be handy
            .maxBy { (_, v) -> v }
        joltage(bank.drop(idx + 1), size - 1, acc + vol)
    }

private fun List<Int>.joinToLong(): Long = joinToString(separator = "").toLong()

private fun parse(input: String): List<BatteryBank> =
    input.lines()
        .map { line ->
            line.toCharArray().map { it.digitToInt() }
        }