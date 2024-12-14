package eu.tinylinden.aoc.y2024.d13

fun clawContraptionOne(input: String): Long =
    clawMachines(input).minTokens()

fun clawContraptionTwo(input: String): Long {
    fun adjust(prize: Prize, adjustment: Long = 10_000_000_000_000) =
        prize.let { (x, y) -> x + adjustment to y + adjustment }

    return clawMachines(input).map { (a, b, prize) -> Triple(a, b, adjust(prize)) }.minTokens()
}

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
private fun clawMachines(input: String): List<ClawMachine> =
    input.lines().filter { it.isNotBlank() }
        .flatMap { it.replace(Regex("[^\\d,]"), "").split(',') }
        .map { it.toLong() }
        .chunked(6) { ClawMachine(it[0] to it[1], it[2] to it[3], it[4] to it[5]) }
