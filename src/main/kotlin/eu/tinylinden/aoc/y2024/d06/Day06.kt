package eu.tinylinden.aoc.y2024.d06

import eu.tinylinden.aoc.base.*

fun guardGallivantOne(input: String): Int =
    Guard(charGrid(input) { throw IndexOutOfBoundsException() }).patrol().visited.size

fun guardGallivantTwo(input: String): Int {
    var result = 0
    charGrid(input) { throw IndexOutOfBoundsException() }.let { grid ->
        grid.filter { (_, c) -> c == '.' }.forEach { (p, c) ->
            grid[p] = '#'
            try {
                Guard(grid).patrol()
            } catch (ex: IllegalStateException) {
                result++
            }
            grid[p] = c
        }
    }
    return result
}

private class Guard(
    private val grid: CharGrid
) {
    private var location: Point = grid.findPoint('^')
    private var heading: CardinalDirection = CardinalDirection.UP

    val visited: MutableSet<Point> = mutableSetOf(location)
    private val obstacles: MutableMap<Point, Int> = mutableMapOf()

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
                    obstacles[p] = ((obstacles[p] ?: 0) + 1).also {
                        if (it > 3) {
                            throw IllegalStateException() // break loop
                        }
                    }
                    heading = heading.right()
                } else {
                    visited += p
                    location = p
                }
            }
    }
}
