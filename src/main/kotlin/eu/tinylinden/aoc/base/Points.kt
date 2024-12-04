package eu.tinylinden.aoc.base

typealias Point = Pair<Int, Int>

fun next(start: Point, direction: Direction): Point =
    (start.first + direction.point.first) to (start.second + direction.point.second)
