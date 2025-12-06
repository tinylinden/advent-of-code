package eu.tinylinden.aoc.y2025.d06

fun trashCompactorOne(input: String): Long =
    parseColumns(input).sumOf { it.answer() }

fun trashCompactorTwo(input: String): Long =
    parseColumns(input).sumOf { it.rotate().answer() }

data class Column(
    val numbers: List<String>,
    val symbol: Char,
) {
    fun answer(): Long =
        numbers.map { it.trim().toLong() }
            .let {
                if (symbol == '+') it.sum() else it.reduce { l, r -> l * r }
            }


    fun rotate(): Column =
        numbers.first()
            .mapIndexed { idx, _ -> numbers.map { it[idx] }.joinToString("") }
            .filter { it.isNotBlank() }
            .let { copy(numbers = it) }
}

private fun parseColumns(input: String): List<Column> {
    tailrec fun part(l: Int, r: Int, symbols: String, numbers: List<String>, acc: List<Column>): List<Column> =
        when {
            l == -1 -> acc
            symbols[l] == ' ' -> part(l - 1, r, symbols, numbers, acc)
            else -> part(l - 1, l, symbols, numbers, acc + Column(numbers.map { it.substring(l, r) }, symbols[l]))
        }

    val lines = input.lines()
    val width = lines.maxOf { it.length }

    return lines
        .map { it.padEnd(width, ' ') } // restore ' ' at the end (removed by ide)
        .let { part(width - 1, width, it.last(), it.dropLast(1), emptyList()) }
}
