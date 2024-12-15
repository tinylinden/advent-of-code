package eu.tinylinden.aoc.base

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
    (first + other.first) to (second + other.second)

operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>): Pair<Int, Int> =
    (first - other.first) to (second - other.second)

operator fun Pair<Int, Int>.times(n: Int): Pair<Int, Int> =
    (first * n) to (second * n)

operator fun Pair<Int, Int>.rem(other: Pair<Int, Int>): Pair<Int, Int> =
    (first % other.first) to (second % other.second)
