package eu.tinylinden.aoc.y2024.d04

import eu.tinylinden.aoc.base.*

// https://adventofcode.com/2024/day/4

fun ceresSearchOne(input: String): Long =
    charGrid(input)
        .words("XMAS", DIRECTIONS_ALL)
        .count()
        .toLong()

fun ceresSearchTwo(input: String): Long =
    charGrid(input)
        .words("MAS", DIRECTIONS_ORDINAL)
        .groupBy { point(it[1]) } // 2nd letter's location
        .count { (_, v) -> v.size == 2 }
        .toLong()
