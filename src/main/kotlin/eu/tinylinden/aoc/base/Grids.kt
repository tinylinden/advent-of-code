package eu.tinylinden.aoc.base

// grid
// +-----+-----+-----+
// |(0,0)|     |     |
// +-----+-----+-----+
// |     |     |     |
// +-----+-----+-----+
// |     |     |(n,n)|
// +-----+-----+-----+

class CharGrid(
    private val delegate: MutableMap<Point, Char>,
    private val onMissing: () -> Char,
) : MutableMap<Point, Char> by delegate {

    fun cell(point: Point): CharCell = point to (this[point] ?: onMissing())

    fun findWords(phrase: String, directions: List<Direction>): List<Word> =
        filterValues { it == phrase.first() }
            .keys
            .flatMap { words(this, it, phrase.length, directions) }
            .filter { string(it) == phrase }

    fun findPoint(char: Char): Point =
        filter { (_, c) -> c == char }
            .map { it.key }
            .first()

    companion object {
        fun parse(string: String, onMissing: () -> Char = { ' ' }): CharGrid =
            string.lines()
                .flatMapIndexed { row, line -> line.mapIndexed { col, char -> (row to col) to char } }
                .toMap()
                .let { CharGrid(it.toMutableMap(), onMissing) }
    }
}

fun charGrid(input: String, onMissing: () -> Char = { ' ' }): CharGrid =
    CharGrid.parse(input, onMissing)
