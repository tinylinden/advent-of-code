package eu.tinylinden.aoc.y2025.d05

fun cafeteriaOne(input: String): Long =
    parse(input).freshAvailable()

fun cafeteriaTwo(input: String): Long =
    parse(input).freshAll()

private data class Ingredients(
    val fresh: List<LongRange>,
    val available: List<Long>,
) {
    fun freshAvailable(): Long = available.count { isFresh(it) }.toLong()

    fun freshAll(): Long {
        var total = 0L
        var max = 0L
        fresh.forEach { range ->
            if (range.last >= max) {
                total += range.last - maxOf(range.first, max) + 1
                max = range.last + 1
            }
        }
        return total
    }

    private fun isFresh(available: Long): Boolean = fresh.any { available in it }
}

private fun parse(input: String): Ingredients {
    val fresh = input.lines()
        .filter { it.contains('-') }
        .map { it.split('-') }
        .map { it[0].toLong()..it[1].toLong() }
        .sortedBy { it.first }

    val available = input.lines()
        .filterNot { it.isBlank() }
        .filterNot { it.contains('-') }
        .map { it.toLong() }

    return Ingredients(fresh, available)
}