package eu.tinylinden.aoc.y2025.d09

import eu.tinylinden.aoc.base.graphs.SimpleWeightedGraph
import kotlin.math.abs

fun movieTheaterOne(input: String): Long {
    val graph = SimpleWeightedGraph(parse(input), area)
    return graph.edges.maxOf { it.third }
}

fun movieTheaterTwo(input: String): Long {
    val reds = SimpleWeightedGraph(parse(input), area)

    return 0
}

private typealias Tail = Pair<Long, Long>

private val area: (Tail, Tail) -> Long = { left, right ->
    val (lx, ly) = left
    val (rx, ry) = right
    (abs(lx - rx) + 1) * (abs(ly - ry) + 1)
}

private fun parse(input: String): List<Pair<Long, Long>> =
    input.lines()
        .map { it.split(',') }
        .map { Tail(it[0].toLong(), it[1].toLong()) }
