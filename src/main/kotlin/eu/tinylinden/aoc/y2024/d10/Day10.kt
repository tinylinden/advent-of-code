package eu.tinylinden.aoc.y2024.d10

import eu.tinylinden.aoc.base.*

fun hoofItOne(input: String): Long = charGrid(input).trails().totalScore()

fun hoofItTwo(input: String): Long = charGrid(input).trails().totalRating()

private fun CharGrid.trails(): Trails {
    fun next(path: Word, section: String) =
        words(this, path.lastPoint(), section, DIRECTIONS_CARDINAL)
            .map { path + it.last() }

    val trailheads = this.words("01", DIRECTIONS_CARDINAL)
    return listOf("12", "23", "34", "45", "56", "67", "78", "89") // sections
        .fold(trailheads) { acc, s -> acc.flatMap { next(it, s) } }
}

private typealias Trails = List<Word>

private fun Trails.totalScore() = distinctBy { it.first() to it.last() }.size.toLong()

private fun Trails.totalRating() = size.toLong()
