package eu.tinylinden.aoc.y2025.d04

fun printingDepartmentOne(input: String): Long {
    val grid = parse(input)
    return grid.map { roll -> countAdjacent(roll, grid) }
        .count { it < 4 }
        .toLong()
}

fun printingDepartmentTwo(input: String): Long = 0

private typealias Roll = Pair<Int, Int>
private typealias Grid = Set<Roll>

private fun countAdjacent(roll: Roll, grid: Grid): Int =
    (grid intersect adjacent(roll)).size

private fun adjacent(roll: Roll): Grid {
    val (x, y) = roll
    return setOf(
        x - 1 to y - 1,
        x to y - 1,
        x + 1 to y - 1,
        x - 1 to y,
        x + 1 to y,
        x - 1 to y + 1,
        x to y + 1,
        x + 1 to y + 1,
    )
}

private fun parse(input: String): Grid =
    input.lines()
        .flatMapIndexed { y, line -> parseLine(y, line) }
        .filterNotNull()
        .toSet()

private fun parseLine(y: Int, line: String): List<Roll?> =
    line.toCharArray()
        .mapIndexed { x, chr ->
            if (chr == '.') {
                null
            } else {
                x to y
            }
        }
