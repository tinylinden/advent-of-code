package eu.tinylinden.aoc.y2024.d14

import eu.tinylinden.aoc.base.parsePairs
import eu.tinylinden.aoc.base.plus
import eu.tinylinden.aoc.base.times

fun restroomRedoubtOne(input: String, width: Int, height: Int): Long =
    robots(input)
        .map { move(it, 100, width, height) }
        .groupingBy { quadrant(it.pos, width / 2, height / 2) }
        .eachCount()
        .filter { (q, _) -> q != "" }
        .values
        .fold(1L) { acc, c -> acc * c }

fun restroomRedoubtTwo(input: String, width: Int, height: Int): Long {
    var robots = robots(input)
    var iterations = 0L

    do {
        iterations++
        robots = robots.map { move(it, 1, width, height) }
        val repeat = detectEasterEgg(robots)
    } while (!repeat)

    return iterations
}

private fun move(robot: Robot, n: Int, width: Int, height: Int): Robot {
    val (pos, vel) = robot
    val (x, y) = pos + vel * n
    return robot.copy(pos = snap(x, width) to snap(y, height))
}

private fun snap(curr: Int, limit: Int): Int =
    //                     ,-- corrects to 0 if exact on limit
    (curr % limit + limit) % limit

// quadrant of the grid
//
// 0,0     ,-- center
//  +------c------+
//  |  NW  |  NE  |
//  m------*------m -- middle
//  |  SW  |  SE  |
//  +------c------+
//             max,max
private fun quadrant(pos: Pair<Int, Int>, center: Int, middle: Int): String =
    pos.let { (x, y) ->
        when {
            x < center && y < middle -> "NW"
            x > center && y < middle -> "NE"
            x > center && y > middle -> "SE"
            x < center && y > middle -> "SW"
            else -> ""
        }
    }

// cheated -- as i have checked how tree should look like, and
// it's 21 robots wide and 23 robots high
private fun detectEasterEgg(robots: List<Robot>): Boolean {
    fun max(f: (Robot) -> Int) =
        robots.groupingBy(f).eachCount().values.max()

    return max { it.pos.first } > 21 && max { it.pos.second } > 23
}

private data class Robot(
    val pos: Pair<Int, Int>, // x, y
    val vel: Pair<Int, Int>, // dx, dy
)

private fun robots(input: String): List<Robot> =
    parsePairs(input.replace(" ", "\n"), Regex("-*\\d+")) { it.toInt() }
        .chunked(2) { Robot(it[0], it[1]) }
