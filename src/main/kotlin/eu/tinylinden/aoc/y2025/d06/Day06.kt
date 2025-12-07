package eu.tinylinden.aoc.y2025.d06

fun trashCompactorOne(input: String): Long =
    parse(input).sumOf { it.answer() }

fun trashCompactorTwo(input: String): Long =
    parse(input).sumOf { it.rotate().answer() }

data class Problem(
    val numbers: List<String>, // spaces preserved as they have meaning for rotating (reading numbers vertically)
    val symbol: Char,
) {
    fun answer(): Long =
        numbers.map { it.trim().toLong() }
            .let { if (symbol == '+') it.sum() else it.reduce { l, r -> l * r } }

    fun rotate(): Problem =
        numbers.first()
            .mapIndexed { i, _ -> numbers.map { it[i] }.joinToString("") }
            .let { copy(numbers = it) }
}

private fun parse(input: String): List<Problem> {
    tailrec fun part(l: Int, r: Int, symbols: String, numbers: List<String>, acc: List<Problem>): List<Problem> {
        fun problem() = Problem(numbers.map { it.substring(l, r - 1) }, symbols[l])

        return when {
            l == -1 -> acc
            symbols[l] == ' ' -> part(l - 1, r, symbols, numbers, acc)
            else -> part(l - 1, l, symbols, numbers, acc + problem())
        }
    }

    val lines = input.lines()
    val width = lines.maxOf { it.length } + 1 // compensate missing space for last column

    return lines
        .map { it.padEnd(width, ' ') } // trailing spaces can be trimmed while ^c^V
        .let { part(width - 1, width, it.last(), it.dropLast(1), emptyList()) }
}
