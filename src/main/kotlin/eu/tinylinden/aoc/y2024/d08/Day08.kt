package eu.tinylinden.aoc.y2024.d08

import eu.tinylinden.aoc.base.*

fun resonantCollinearityOne(input: String): Int = antinodesCount(charGrid(input), Closest)

fun resonantCollinearityTwo(input: String): Int = antinodesCount(charGrid(input), Resonant)

private fun antinodesCount(grid: CharGrid, f: Finder): Int =
    grid.pointsByChar { it != '.' }
        .values
        .filter { it.size > 1 }
        .flatMap { antinodes(it, grid, f) }
        .toSet()
        .size

private fun antinodes(antennas: List<Point>, grid: CharGrid, f: Finder): List<Point> {
    fun partial(head: Point, rest: List<Point>): List<Point> =
        when (rest.size) {
            0 -> emptyList()
            1 -> f(head, rest[0], grid)
            else -> f(head, rest[0], grid) + partial(head, rest.drop(1))
        }

    return antennas.flatMapIndexed { i, p -> partial(p, antennas.drop(i + 1)) }
}

private fun interface Finder : (Point, Point, CharGrid) -> List<Point>

private val Closest = Finder { l, r, grid ->
    val delta = r - l
    listOf(l - delta, r + delta).filter { grid.contains(it) }
}

private val Resonant = Finder { l, r, grid ->
    fun partial(seed: Point, next: (Point) -> Point) =
        generateSequence(seed, next).takeWhile { grid.contains(it) }

    val delta = r - l
    (partial(l) { it - delta } + partial(r) { it + delta }).toList()
}
