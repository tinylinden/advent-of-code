package eu.tinylinden.aoc.y2024.d14

import eu.tinylinden.aoc.base.parsePairs
import eu.tinylinden.aoc.base.plus
import eu.tinylinden.aoc.base.times

fun restroomRedoubtOne(input: String, rows: Int, cols: Int): Long =
    robots(input)
        .map { move(it, 100, rows, cols) }
        .groupingBy { quadrant(it, rows / 2, cols / 2) }
        .eachCount()
        .filter { (q, _) -> q != "" }
        .values
        .fold(1L) { acc, c -> acc * c }

fun restroomRedoubtTwo(input: String): Long = 0

private fun move(robot: Robot, n: Int, rows: Int, cols: Int): Robot {
    val (pos, vel) = robot
    return (pos + vel * n).let { (row, col) ->
        robot.copy(pos = snap(row, rows) to snap(col, cols))
    }
}

private fun snap(curr: Int, limbo: Int): Int =
    when { // must be a nicer way, but i am tired
        curr % limbo == 0 -> 0
        curr < 0 -> limbo + (curr % limbo)
        curr >= limbo -> curr % limbo
        else -> curr
    }

private fun quadrant(robot: Robot, middleRow: Int, centerCol: Int): String =
    robot.pos.let { (row, col) ->
        when {
            row < middleRow && col < centerCol -> "NW"
            row > middleRow && col < centerCol -> "NE"
            row > middleRow && col > centerCol -> "SE"
            row < middleRow && col > centerCol -> "SW"
            else -> ""
        }
    }

private data class Robot(val pos: Pair<Int, Int>, val vel: Pair<Int, Int>)

private fun robots(input: String): List<Robot> =
    parsePairs(input.replace(" ", "\n"), Regex("-*\\d+")) { it.toInt() }
        .chunked(2) { Robot(it[0], it[1]) }
