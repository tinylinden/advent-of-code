package eu.tinylinden.aoc.y2024.d04

// https://adventofcode.com/2024/day/4

fun ceresSearchOne(input: String): Int {
    val grid = grid(input)
    val vectors = cardinal + diagonal
    return grid.filterValues { it == 'X' }
        .keys
        .flatMap { loc -> vectors.map { word(grid, loc, 4, it) } }
        .count { string(it) == "XMAS" }
}

fun ceresSearchTwo(input: String): Int {
    val grid = grid(input)
    return grid.filterValues { it == 'M' }
        .keys
        .flatMap { loc -> diagonal.map { word(grid, loc, 3, it) } }
        .filter { string(it) == "MAS" }
        .groupingBy { it[1].first } // location of second letter
        .eachCount()
        .count { (_, v) -> v == 2 }
}

private fun grid(input: String): Grid =
    input.lines().flatMapIndexed { row, line ->
        line.mapIndexed { col, char -> (row to col) to char }
    }.toMap()

private fun word(grid: Grid, start: Loc, len: Int, vec: Vec): Word =
    generateSequence(start) { it + vec }
        .map { letter(grid, it) }
        .take(len)
        .toList()

private fun string(word: Word): String =
    word.map { it.second }.joinToString("")

private fun letter(grid: Grid, loc: Loc): Letter =
    grid[loc]?.let { loc to it } ?: (loc to '.')

private operator fun Loc.plus(other: Loc): Loc =
    (first + other.first) to (second + other.second)

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
