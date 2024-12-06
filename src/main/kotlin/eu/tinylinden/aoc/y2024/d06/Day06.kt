package eu.tinylinden.aoc.y2024.d06

import eu.tinylinden.aoc.base.*

fun guardGallivantOne(input: String): Int =
    Guard(charGrid(input) { throw IndexOutOfBoundsException() }).patrol().visited.size

fun guardGallivantTwo(input: String): Int {
    val original = charGrid(input) { throw IndexOutOfBoundsException() }
    return original.points('.')
        .parallelStream()
        .filter {
            try {
                // there must be a better and faster way of loop detection
                Guard(original.mutated(it to '#')).patrol()
                false
            } catch (ex: IllegalStateException) {
                true
            }
        }
        .count()
        .toInt()
}

private class Guard(
    private val grid: CharGrid
) {
    private var location: Point = grid.point('^')
    private var heading: CardinalDirection = CardinalDirection.UP

    val visited: MutableSet<Point> = mutableSetOf(location)
    private val obstacles: MutableSet<Pair<Point, Point>> = mutableSetOf()

    fun patrol(): Guard {
        try {
            while (true) {
                move()
            }
        } catch (ex: IndexOutOfBoundsException) {
            // just vanish outside the grid
        }
        return this
    }

    private fun move() {
        grid.cell(next(location, heading))
            .let { (p, c) ->
                if (c == '#') {
                    if (!obstacles.add(location to p)) {
                        throw IllegalStateException() // break loop
                    }
                    heading = heading.right()
                } else {
                    visited += p
                    location = p
                }
            }
    }
}
