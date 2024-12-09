package eu.tinylinden.aoc.base

// point on grid - row, column
typealias Point = Pair<Int, Int>

fun Point.row(): Int = first

fun Point.col(): Int = second

fun next(start: Point, direction: Direction): Point =
    (start.first + direction.delta.first) to (start.second + direction.delta.second)

fun pointsComparator(): Comparator<Point> = compareBy({ it.row() }, { it.col() })

operator fun Point.plus(other: Point): Point =
    (first + other.first) to (second + other.second)

operator fun Point.minus(other: Point): Point =
    (first - other.first) to (second - other.second)
