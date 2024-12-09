package eu.tinylinden.aoc.y2024.d08

import eu.tinylinden.aoc.base.*

fun resonantCollinearityOne(input: String): Int {
    val grid = charGrid(input)
    return grid.pointsByChar { it != '.' }
        .filter { (_, p) -> p.size > 1 }
        .values
        .flatMap { antinodes(it) }
        .filter { grid.contains(it) }
        .toSet()
        .size
}

fun resonantCollinearityTwo(input: String): Int =
    0

private fun antinodes(points: List<Point>): Set<Point> =
    points.flatMapIndexed { i, p -> antinodes(p, points.drop(i + 1)) }.toSet()

private fun antinodes(point: Point, rest: List<Point>): Set<Point> =
    when (rest.size) {
        0 -> emptySet()
        1 -> antinodes(point, rest[0])
        else -> antinodes(point, rest[0]) + antinodes(point, rest.drop(1))
    }

private fun antinodes(l: Point, r: Point): Set<Point> =
    (r - l).let { setOf(l - it, r + it) }
