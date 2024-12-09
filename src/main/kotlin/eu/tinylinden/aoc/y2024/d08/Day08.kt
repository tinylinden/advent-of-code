package eu.tinylinden.aoc.y2024.d08

import eu.tinylinden.aoc.base.*

fun resonantCollinearityOne(input: String): Int = countAntinodes(charGrid(input), Single)

fun resonantCollinearityTwo(input: String): Int = countAntinodes(charGrid(input), Resonant)

private fun countAntinodes(grid: CharGrid, f: Finder): Int =
    grid.pointsByChar { it != '.' }
        .values
        .filter { it.size > 1 }
        .flatMap { antinodes(it, grid, f) }
        .toSet()
        .size

private fun antinodes(points: List<Point>, grid: CharGrid, f: Finder): List<Point> {
    fun part(p: Point, rest: List<Point>): List<Point> =
        when (rest.size) {
            0 -> emptyList()
            1 -> f(p, rest[0], grid)
            else -> f(p, rest[0], grid) + part(p, rest.drop(1))
        }

    return points.flatMapIndexed { i, p -> part(p, points.drop(i + 1)) }
}

private fun interface Finder : (Point, Point, CharGrid) -> List<Point>

private val Single = Finder { l, r, grid ->
    (r - l).let { listOf(l - it, r + it) }.filter { grid.contains(it) }
}

private val Resonant = Finder { l, r, grid ->
    fun part(seed: Point, next: (Point) -> Point) =
        generateSequence(seed, next).takeWhile { grid.contains(it) }.toList()

    val delta = (r - l)
    part(l) { it - delta } + part(r) { it + delta }
}
