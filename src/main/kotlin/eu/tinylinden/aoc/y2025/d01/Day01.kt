package eu.tinylinden.aoc.y2025.d01

import kotlin.math.abs

fun secretEntranceOne(input: String): Long =
    parse(input)
        .fold(FastDial()) { dial, clicks -> dial.rotate(clicks) }
        .counter

fun secretEntranceTwo(input: String): Long =
    parse(input)
        .fold(PreciseDial()) { dial, clicks -> dial.rotate(clicks) }
        .counter

private data class FastDial(
    val position: Long = 50,
    val counter: Long = 0,
) {
    fun rotate(clicks: Int): FastDial {
        val next = position + clicks
        return if (next % 100 == 0L) {
            copy(position = next, counter = counter + 1)
        } else {
            copy(position = next)
        }
    }
}

private data class PreciseDial(
    val position: Long = 50,
    val counter: Long = 0,
) {
    fun rotate(clicks: Int): PreciseDial {
        val next = position + clicks
        return if (clicks > 0) {
            copy(position = next, counter = counter + count(clicks, 1))
        } else {
            copy(position = next, counter = counter + count(clicks, -1))
        }
    }

    private fun count(clicks: Int, direction: Int): Int =
        generateSequence(position) { it + direction }
            .take(abs(clicks))
            .count { it % 100 == 0L }
}

private fun parse(input: String): List<Int> =
    input.lines()
        .map { line ->
            line.drop(1).toInt() * if (line.startsWith('R')) 1 else -1
        }
