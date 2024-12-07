package eu.tinylinden.aoc.y2024.d06

import eu.tinylinden.aoc.base.*

fun guardGallivantOne(input: String): Long =
    walker(charGrid(input)).patrol().size.toLong()

fun guardGallivantTwo(input: String): Long {
    val original = charGrid(input)
    val start = original.point('^')

    fun partial(point: Point): Int =
        looper(original.mutated(point to '#'), start).patrol()

    return (walker(original, start).patrol() - start)
        .parallelStream()
        .reduce(0, { acc, p -> acc + partial(p) }, { l, r -> l + r })
        .toLong()
}

private fun walker(grid: CharGrid, start: Point = grid.point('^')) = Guard {
    var location = start
    var heading = CardinalDirection.UP
    val visited = mutableSetOf(start)

    do {
        val repeat = grid.cell(next(location, heading)).let { (p, c) ->
            when (c) {
                ' ' -> false

                '#' -> {
                    heading = heading.right()
                    true
                }

                else -> {
                    visited += p
                    location = p
                    true
                }
            }
        }
    } while (repeat)

    visited
}

private fun looper(grid: CharGrid, start: Point) = Guard {
    var location = start
    var heading = CardinalDirection.UP
    var noLoop = true
    val obstacles = mutableSetOf<Pair<Direction, Point>>()

    do {
        val repeat = grid.cell(next(location, heading)).let { (p, c) ->
            when (c) {
                ' ' -> false

                '#' -> {
                    noLoop = obstacles.add(heading to p)
                    heading = heading.right()
                    noLoop
                }

                else -> {
                    location = p
                    true
                }
            }
        }
    } while (repeat)

    if (noLoop) 0 else 1
}

private fun interface Guard<T> {
    fun patrol(): T
}
