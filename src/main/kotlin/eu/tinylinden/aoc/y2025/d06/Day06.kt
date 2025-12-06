package eu.tinylinden.aoc.y2025.d06

fun trashCompactorOne(input: String): Long =
    parseOne(input).sumOf { it.solution() }

fun trashCompactorTwo(input: String): Long =
    parseTwo(input).sumOf { it.solution() }

private data class Problem(
    val numbers: List<Long>,
    val symbol: String,
) {
    fun solution(): Long =
        if (symbol == "+") {
            numbers.sum()
        } else { // *
            numbers.reduce { l, r -> l * r }
        }
}

private fun parseOne(input: String): List<Problem> {
    val (symbols, numbers) = input.lines()
        .map { it.trim().split(Regex("\\s+")) }
        .let { it.last() to it.dropLast(1) }

    return symbols.mapIndexed { idx, symbol ->
        Problem(numbers.map { it[idx].toLong() }, symbol)
    }
}

// ugly, but working
private fun parseTwo(input: String): List<Problem> {
    val max = input.lines().maxOf { it.length }
    val (symbols, numbers) = input.lines()
        .map { it.padEnd(max, ' ') } // justify all lines
        .let { it.last() to it.dropLast(1) }

    val parsed = (symbols.mapIndexedNotNull { i, c -> i.takeIf { c != ' ' } } + max) // find columns boundaries
        .zipWithNext()
        .map { (l, r) -> numbers.map { it.substring(l, r) } } // separate columns
        .map { rotateLeft(it) }
        .zip(symbols.replace(" ", "").chunked(1))
        .map { (n, s) -> Problem(n.map { it.trim().toLong() }, s) }

    return parsed
}

private fun rotateLeft(input: List<String>): List<String> =
    input.first()
        .mapIndexed { i, _ -> input.map { it[i] }.joinToString("") }
        .filter { it.isNotBlank() }
