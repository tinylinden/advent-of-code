package eu.tinylinden.aoc.base

// grid
// +-----+-----+-----+
// |(0,0)|     |     |
// +-----+-----+-----+
// |     |     |     |
// +-----+-----+-----+
// |     |     |(n,n)|
// +-----+-----+-----+

interface CharGrid {
    fun cell(point: Point): CharCell
    fun words(phrase: String, directions: List<Direction>): List<Word>
    fun point(char: Char): Point
    fun points(char: Char): List<Point>

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
            .flatMap { words(this, it, phrase.length, directions) }
            .filter { string(it) == phrase }

    override fun point(char: Char): Point =
        cells.firstNotNullOf { (p, c) -> p.takeIf { c == char } }

    override fun points(char: Char): List<Point> =
        cells.filter { (_, c) -> c == char }.map { (p, _) -> p }
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
        .let { BasicCharGrid(it.toMutableMap(), onMissing) }
