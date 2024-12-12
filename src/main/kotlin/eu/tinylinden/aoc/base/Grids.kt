package eu.tinylinden.aoc.base

import kotlin.math.max
import kotlin.math.sqrt

// grid
// +-----+-----+-----+
// |(0,0)|(0,1)|     |
// +-----+-----+-----+
// |(1,0)|     |     |
// +-----+-----+-----+
// |     |     |(n,n)|
// +-----+-----+-----+

interface CharGrid {
    fun cell(point: Point): CharCell

    fun words(phrase: String, directions: List<Direction>): List<Word>

    fun contains(point: Point): Boolean

    fun point(char: Char): Point
    fun points(char: Char): List<Point>
    fun pointsByChar(predicate: (Char) -> Boolean = { true }): Map<Char, List<Point>>
    fun border(predicate: (Char) -> Boolean = { true }): List<Point>

    fun mutated(mutation: CharCell): CharGrid = MutatedCharGrid(this, mutation)
}

private class BasicCharGrid(
    private val cells: Map<Point, Char>,
    private val onMissing: () -> Char,
) : CharGrid {
    override fun cell(point: Point): CharCell = point to (cells[point] ?: onMissing())

    override fun words(phrase: String, directions: List<Direction>): List<Word> =
        cells.filterValues { it == phrase.first() }
            .keys
            .flatMap { words(this, it, phrase, directions) }

    override fun contains(point: Point): Boolean = cells.keys.contains(point)

    override fun point(char: Char): Point =
        cells.firstNotNullOf { (p, c) -> p.takeIf { c == char } }

    override fun points(char: Char): List<Point> =
        cells.filter { (_, c) -> c == char }.map { (p, _) -> p }

    override fun pointsByChar(predicate: (Char) -> Boolean): Map<Char, List<Point>> =
        cells.entries
            .filter { (_, c) -> predicate(c) }
            .groupBy({ (_, c) -> c }, { (p, _) -> p })

    override fun border(predicate: (Char) -> Boolean): List<Point> =
        cells.entries
            .filter { (p, _) -> p.row() == 0 || p.col() == 0 || p.row() == maxPoint.row() || p.col() == maxPoint.col() }
            .filter { (_, c) -> predicate(c) }
            .map { (p, _) -> p }

    override fun toString(): String =
        cells.toSortedMap(pointsComparator())
            .values
            .chunked(maxPoint.col() + 1) { it.joinToString("") }
            .joinToString("\n")

    private val maxPoint by lazy {
        cells.keys.maxWith(pointsComparator())
    }
}

private class MutatedCharGrid(
    private val base: CharGrid,
    private val mutation: CharCell,
) : CharGrid by base {
    override fun cell(point: Point): CharCell =
        mutation.takeIf { (p, _) -> p == point } ?: base.cell(point)
}

fun charGrid(input: String, onMissing: () -> Char = { ' ' }): CharGrid =
    input.lines()
        .flatMapIndexed { row, line -> line.mapIndexed { col, char -> (row to col) to char } }
        .toMap()
        .let { BasicCharGrid(it, onMissing) }
