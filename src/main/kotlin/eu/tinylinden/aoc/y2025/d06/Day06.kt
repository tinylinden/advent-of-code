package eu.tinylinden.aoc.y2025.d06

fun trashCompactorOne(input: String): Long =
    parse(input).sumOf { it.solution() }

fun trashCompactorTwo(input: String): Long = 0

private data class Problem(
    val numbers: List<Long>,
    val symbol: String,
) {
    fun solution(): Long =
        if (symbol == "+") {
            numbers.fold(0L) { l, r -> l + r }
        } else { // *
            numbers.fold(1L) { l, r -> l * r }
        }
}

private fun parse(input: String): List<Problem> {
    val (symbols, numbers) = input.lines()
        .map { it.trim().split(Regex("\\s+")) }
        .let { it.last() to it.dropLast(1) }

    return symbols.mapIndexed { idx, symbol ->
        Problem(numbers.map { it[idx].toLong() }, symbol)
    }
}