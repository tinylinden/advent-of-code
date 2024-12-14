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
                da % d != 0L || db % d != 0L -> 0 // fractions impossible
                a < 0 || b < 0 -> 0               // negatives impossible
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
private fun clawMachines(input: String): List<ClawMachine> {
    val cache = input.lines().filter { it.isNotBlank() }.groupBy { it.substringBefore(":") }

    val a = cache["Button A"]!!.map { pair(it) }
    val b = cache["Button B"]!!.map { pair(it) }
    val p = cache["Prize"]!!.map { pair(it) }

    return a.indices.map { ClawMachine(a[it], b[it], p[it]) }
}

private fun pair(input: String): Pair<Long, Long> =
    input.replace(Regex("[^\\d,]"), "").split(',')
        .let { it[0].toLong() to it[1].toLong() }
