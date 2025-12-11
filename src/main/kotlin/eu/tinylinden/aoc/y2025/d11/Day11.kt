package eu.tinylinden.aoc.y2025.d11

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph

fun reactorOne(input: String): Long {
    val graph = graph(input)
    return countPaths("you", "out", graph)
}

fun reactorTwo(input: String): Long {
    val graph = graph(input)
    return listOf("svr", "fft", "dac", "out")
        .zipWithNext()
        .map { (start, end) -> countPaths(start, end, graph) }
        .reduce { acc, curr -> acc * curr }
}

private fun countPaths(start: String, end: String, graph: Graph<String, DefaultEdge>): Long {
    // memoized dfs
    fun part(curr: String, buf: MutableMap<String, Long>): Long =
        if (curr == end) {
            1
        } else {
            graph.outgoingEdgesOf(curr)
                .map { graph.getEdgeTarget(it) }
                .sumOf { out -> buf.getOrPut(out) { part(out, buf) } }
        }

    return part(start, mutableMapOf())
}

private fun graph(input: String): Graph<String, DefaultEdge> {
    val gb = DirectedAcyclicGraph.createBuilder<String, DefaultEdge>(DefaultEdge::class.java)
    input.lines().forEach { line ->
        val buf = line.split(Regex(":? "))
        buf.drop(1).forEach { gb.addEdge(buf[0], it) }
    }
    return gb.build()
}