package eu.tinylinden.aoc.y2024.d04

import eu.tinylinden.aoc.base.CharGrid
import eu.tinylinden.aoc.base.DIRECTIONS_ALL
import eu.tinylinden.aoc.base.DIRECTIONS_ORDINAL
import eu.tinylinden.aoc.base.point

// https://adventofcode.com/2024/day/4

fun ceresSearchOne(input: String): Int =
    CharGrid.parse(input)
        .findWords("XMAS", DIRECTIONS_ALL)
        .count()

fun ceresSearchTwo(input: String): Int =
    CharGrid.parse(input)
        .findWords("MAS", DIRECTIONS_ORDINAL)
        .groupBy { point(it[1]) } // 2nd letter's location
        .count { (_, v) -> v.size == 2 }
