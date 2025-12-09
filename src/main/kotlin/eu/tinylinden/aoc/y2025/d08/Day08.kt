package eu.tinylinden.aoc.y2025.d08

fun playgroundOne(input: String, n: Int): Long {
    val graph = Graph(parse(input))
    val circuits = circuits(graph, n)
    return circuits.take(3).fold(1L) { acc, c -> acc * c.size }
}

fun playgroundTwo(input: String): Long {
    val graph = Graph(parse(input))
    val lastConnection = lastConnection(graph)
    return lastConnection.first.first * lastConnection.second.first
}

private typealias Vertex = Triple<Long, Long, Long> // junction box
private typealias Edge = Triple<Vertex, Vertex, Long> // distance

// https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
private fun circuits(graph: Graph, n: Int): List<Set<Vertex>> {
    val circuits = graph.vertices.associateWith { mutableSetOf(it) }.toMutableMap()

    for (idx in 0..<n) {
        val (left, right) = graph.edges[idx].let { (l, r) -> circuits[l]!! to circuits[r]!! }
        if (left === right) continue

        left.addAll(right)
        right.forEach { circuits[it] = left }
    }

    return circuits.values.distinct().sortedByDescending { it.size }
}

private fun lastConnection(graph: Graph): Edge {
    val circuits = graph.vertices.associateWith { mutableSetOf(it) }.toMutableMap()

    var idx = 0
    do {
        val (left, right) = graph.edges[idx].let { (l, r) -> circuits[l]!! to circuits[r]!! }
        if (left != right) {
            left.addAll(right)
            right.forEach { circuits[it] = left }
        }

        if (circuits.values.distinct().size == 1) break
        idx++
    } while (true)

    return graph.edges[idx]
}

private data class Graph(
    val vertices: List<Vertex>,
) {
    val edges: List<Edge> = edges(0, mutableListOf())

    private tailrec fun edges(idx: Int, acc: MutableList<Edge>): List<Edge> =
        if (idx == vertices.size) {
            acc.sortedBy { it.third }
        } else {
            val part = vertices.subList(idx + 1, vertices.size).map { edge(vertices[idx], it) }
            acc.addAll(part) // faster than List + List
            edges(idx + 1, acc)
        }
}

private fun edge(left: Vertex, right: Vertex): Edge {
    // https://en.wikipedia.org/wiki/Euclidean_distance
    val distance = (left.toList() zip right.toList()).sumOf { (l, r) -> (l - r) * (l - r) }
    return Edge(left, right, distance)
}

private fun parse(input: String): List<Vertex> =
    input.lines()
        .map { it.split(',') }
        .map { Vertex(it[0].toLong(), it[1].toLong(), it[2].toLong()) }
