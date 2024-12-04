package eu.tinylinden.aoc.y2024.d04

// https://adventofcode.com/2024/day/4

fun ceresSearchOne(input: String): Int =
    ceresSearch(grid(input), "XMAS", cardinal + diagonal)
        .count()

fun ceresSearchTwo(input: String): Int =
    ceresSearch(grid(input), "MAS", diagonal)
        .groupBy { loc(it, 1) } // by 2nd letter's location
        .count { (_, v) -> v.size == 2 }

private fun ceresSearch(grid: Grid, phrase: String, vectors: List<Vec>): Words =
    grid.filterValues { it == phrase.first() }
        .keys
        .flatMap { loc -> vectors.map { word(grid, loc, phrase.length, it) } }
        .filter { string(it) == phrase }

private fun grid(input: String): Grid =
    input.lines()
        .flatMapIndexed { row, line -> line.mapIndexed { col, char -> (row to col) to char } }
        .toMap()

private fun word(grid: Grid, start: Loc, len: Int, vec: Vec): Word =
    generateSequence(start) { it + vec }
        .map { letter(grid, it) }
        .take(len)
        .toList()

private fun string(word: Word): String =
    word.map { it.second }.joinToString("")

private fun letter(grid: Grid, loc: Loc): Letter =
    grid[loc]?.let { loc to it } ?: (loc to '.')

private operator fun Loc.plus(vec: Vec): Loc =
    (first + vec.first) to (second + vec.second)

private fun loc(word: Word, index: Int): Loc =
    word[index].first

private val cardinal = listOf(
    1 to 0,   // right
    0 to 1,   // down
    -1 to 0,  // left
    0 to -1,  // up
)

private val diagonal = listOf(
    1 to 1,   // right-down
    -1 to -1, // left-down
    -1 to 1,  // left-up
    1 to -1,  // right-up
)

private typealias Loc = Pair<Int, Int>
private typealias Vec = Pair<Int, Int>
private typealias Grid = Map<Loc, Char>
private typealias Letter = Pair<Loc, Char>
private typealias Word = List<Letter>
private typealias Words = List<Word>
