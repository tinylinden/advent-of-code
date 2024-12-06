package eu.tinylinden.aoc.y2024.d06

import eu.tinylinden.aoc.base.*

fun guardGallivantOne(input: String): Int =
    Guard(charGrid(input) { throw IndexOutOfBoundsException() }).patrol().visited.size

fun guardGallivantTwo(input: String): Int {
    val original = charGrid(input) { throw IndexOutOfBoundsException() }
    val start = original.point('^')

    fun partial(point: Point): Int =
        try {
            Guard(original.mutated(point to '#'), start).patrol()
            0
        } catch (ex: IllegalStateException) {
            1
        }

    return (Guard(original, start).patrol().visited - start)
        .parallelStream()
        .reduce(0, { acc, p -> acc + partial(p) }, { l, r -> l + r })
}

private class Guard(
    private val grid: CharGrid,
    start: Point = grid.point('^')
) {
    private var location: Point = start
    private var heading: CardinalDirection = CardinalDirection.UP

    val visited: MutableSet<Point> = mutableSetOf(start)
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
