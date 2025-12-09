package eu.tinylinden.aoc.y2025.d08

fun playgroundOne(input: String, n: Int): Long {
    val graph = Graph(parse(input))
    val circuits = circuits(graph, n)
    return circuits.take(3).fold(1L) { acc, c -> acc * c.size }
}

fun playgroundTwo(input: String): Long = 0

private typealias Vertex = Triple<Long, Long, Long> // junction box
private typealias Edge = Triple<Vertex, Vertex, Long> // distance

// https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
private fun circuits(graph: Graph, n: Int): List<Set<Vertex>> {
    val circuits = graph.vertices.associateWith { mutableSetOf(it) }.toMutableMap()

    tailrec fun reduce(idx: Int, acc: Int) {
        if (idx == n) return

        val (left, right) = graph.edges[idx].let { (l, r) -> circuits[l]!! to circuits[r]!! }
        if (left === right) {
            reduce(idx + 1, acc)
        } else {
            left.addAll(right)
            right.forEach { circuits[it] = left }
            reduce(idx + 1, acc + 1)
        }
    }

    reduce(0, 1)
    return circuits.values.distinct().sortedByDescending { it.size }
}

private data class Graph(
    val vertices: List<Vertex>,
) {
    val edges: List<Edge> = es(0, mutableListOf())

    private tailrec fun es(idx: Int, acc: MutableList<Edge>): List<Edge> =
        if (idx == vertices.size) {
            acc.sortedBy { it.third }
        } else {
            val part = vertices.subList(idx + 1, vertices.size).map { edge(vertices[idx], it) }
            acc.addAll(part) // faster than List + List
            es(idx + 1, acc)
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
