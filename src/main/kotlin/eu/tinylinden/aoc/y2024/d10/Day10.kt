package eu.tinylinden.aoc.y2024.d10

import eu.tinylinden.aoc.base.*

fun hoofItOne(input: String): Long =
    trails(charGrid(input))
        .distinctBy { it.first() to it.last() }
        .size.toLong()

fun hoofItTwo(input: String): Long = 0

private fun trails(grid: CharGrid): List<Word> {
    fun next(path: Word, section: String) =
        words(grid, path.lastPoint(), section, DIRECTIONS_CARDINAL)
            .map { path + it.last() }

    val trailheads = grid.words("01", DIRECTIONS_CARDINAL)
    return listOf("12", "23", "34", "45", "56", "67", "78", "89") // sections
        .fold(trailheads) { acc, s -> acc.flatMap { next(it, s) } }
}
