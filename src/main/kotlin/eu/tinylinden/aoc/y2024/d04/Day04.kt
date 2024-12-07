package eu.tinylinden.aoc.y2024.d04

import eu.tinylinden.aoc.base.*

// https://adventofcode.com/2024/day/4

fun ceresSearchOne(input: String): Int =
    charGrid(input)
        .words("XMAS", DIRECTIONS_ALL)
        .count()

fun ceresSearchTwo(input: String): Int =
    charGrid(input)
        .words("MAS", DIRECTIONS_ORDINAL)
        .groupBy { point(it[1]) } // 2nd letter's location
        .count { (_, v) -> v.size == 2 }
