package eu.tinylinden.aoc.y2024.d14

import eu.tinylinden.aoc.base.parsePairs
import eu.tinylinden.aoc.base.plus
import eu.tinylinden.aoc.base.times

fun restroomRedoubtOne(input: String, width: Int, height: Int): Long =
    robots(input)
        .map { move(it, 100, width, height) }
        .groupingBy { quadrant(it, width / 2, height / 2) }
        .eachCount()
        .filter { (q, _) -> q != "" }
        .values
        .fold(1L) { acc, c -> acc * c }

fun restroomRedoubtTwo(input: String): Long = 0

private fun move(robot: Robot, n: Int, width: Int, height: Int): Robot {
    val (x, y) = robot.let { (pos, vel) -> pos + vel * n }
    return robot.copy(pos = snap(x, width) to snap(y, height))
}

private fun snap(curr: Int, limit: Int): Int = (curr % limit + limit) % limit

private fun quadrant(robot: Robot, center: Int, middle: Int): String =
    robot.pos.let { (x, y) ->
        when {
            x < center && y < middle -> "NW"
            x > center && y < middle -> "NE"
            x > center && y > middle -> "SE"
            x < center && y > middle -> "SW"
            else -> ""
        }
    }

private data class Robot(
    val pos: Pair<Int, Int>, // x, y
    val vel: Pair<Int, Int>, // dx, dy
)

private fun robots(input: String): List<Robot> =
    parsePairs(input.replace(" ", "\n"), Regex("-*\\d+")) { it.toInt() }
        .chunked(2) { Robot(it[0], it[1]) }
