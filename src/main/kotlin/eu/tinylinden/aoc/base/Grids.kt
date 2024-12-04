package eu.tinylinden.aoc.base

class CharGrid(
    private val delegate: Map<Point, Char>,
    private val onMissing: () -> Char,
) {

    fun charCell(point: Point): CharCell = point to (delegate[point] ?: onMissing())

    fun findWords(phrase: String, directions: List<Direction>): List<Word> =
        delegate.filterValues { it == phrase.first() }
            .keys
            .flatMap { words(this, it, phrase.length, directions) }
            .filter { string(it) == phrase }

    companion object {
        fun parse(string: String, onMissing: () -> Char = { ' ' }): CharGrid =
            string.lines()
                .flatMapIndexed { row, line -> line.mapIndexed { col, char -> (row to col) to char } }
                .toMap()
                .let { CharGrid(it, onMissing) }
    }
}
