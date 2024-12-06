package eu.tinylinden.aoc.base

typealias Word = List<CharCell>

fun word(grid: CharGrid, start: Point, length: Int, direction: Direction): Word =
    generateSequence(start) { next(it, direction) }
        .map { grid.cell(it) }
        .take(length)
        .toList()

fun words(grid: CharGrid, start: Point, length: Int, directions: List<Direction>): List<Word> =
    directions.map { word(grid, start, length, it) }

fun string(word: Word): String = word.map { it.second }.joinToString("")
