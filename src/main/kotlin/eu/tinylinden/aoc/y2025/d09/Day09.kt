package eu.tinylinden.aoc.y2025.d09

import kotlin.math.abs

fun movieTheaterOne(input: String): Long {
    val graph = Graph(parse(input))
    return graph.edges.maxOf { it.third }
}

fun movieTheaterTwo(input: String): Long {
    val reds = Graph(parse(input))

    return 0
}

private typealias Vertex = Pair<Long, Long>
private typealias Edge = Triple<Vertex, Vertex, Long>

private class Graph(
    val vertices: List<Vertex>
) {
    val edges: List<Edge> = edges(0, mutableListOf())

    private tailrec fun edges(idx: Int, acc: MutableList<Edge>): List<Edge> =
        if (idx == vertices.size) {
            acc
        } else {
            val part = vertices.subList(idx + 1, vertices.size).map { edge(vertices[idx], it) }
            acc.addAll(part)
            edges(idx + 1, acc)
        }
}

private fun edge(left: Vertex, right: Vertex): Edge {
    val (lx, ly) = left
    val (rx, ry) = right
    val area = (abs(lx - rx) + 1) * (abs(ly - ry) + 1)
    return Edge(left, right, area)
}

private fun parse(input: String): List<Pair<Long, Long>> =
    input.lines()
        .map { it.split(',') }
        .map { Vertex(it[0].toLong(), it[1].toLong()) }
