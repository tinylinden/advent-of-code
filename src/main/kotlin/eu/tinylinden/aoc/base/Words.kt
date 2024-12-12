package eu.tinylinden.aoc.base

typealias Word = List<CharCell>

fun word(grid: CharGrid, start: Point, length: Int, direction: Direction): Word =
    generateSequence(start) { next(it, direction) }
        .map { grid.cell(it) }
        .take(length)
        .toList()

fun words(grid: CharGrid, start: Point, phrase: String, directions: List<Direction>): List<Word> =
    words(grid, start, phrase.length, directions).filter { string(it) == phrase }

fun words(grid: CharGrid, start: Point, length: Int, directions: List<Direction>): List<Word> =
    directions.map { word(grid, start, length, it) }

fun string(word: Word): String = word.map { it.second }.joinToString("")

fun dump(word: Word): String =
    word.joinToString(prefix = "${string(word)}: ") { (p, _) -> "$p" }

fun Word.lastPoint(): Point = point(last())

fun Word.contains(point: Point): Boolean = any { (p, _) -> p == point }
