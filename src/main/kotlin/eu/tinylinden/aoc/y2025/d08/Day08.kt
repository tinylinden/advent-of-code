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
    val cs = graph.vs.associateWith { mutableSetOf(it) }.toMutableMap()

    tailrec fun reduce(idx: Int, acc: Int) {
        if (idx == n) return

        val (l, r) = graph.es[idx]
        val cl = cs[l]!!
        if (l in cl && r in cl) {
            reduce(idx + 1, acc)
        } else {
            val cr = cs[r]!!
            cl.addAll(cr)
            cr.forEach { cs[it] = cl }
            reduce(idx + 1, acc + 1)
        }
    }

    reduce(0, 1)
    return cs.values.distinct().sortedByDescending { it.size }
}

private data class Graph(
    val vs: List<Vertex>,
) {
    val es: List<Edge> = es(0, mutableListOf())

    private tailrec fun es(idx: Int, acc: MutableList<Edge>): List<Edge> =
        if (idx == vs.size) {
            acc.sortedBy { it.third }
        } else {
            val part = vs.subList(idx + 1, vs.size).map { edge(vs[idx], it) }
            acc.addAll(part) // faster than List + List
            es(idx + 1, acc)
        }
}

private fun edge(left: Vertex, right: Vertex): Edge {
    // https://en.wikipedia.org/wiki/Euclidean_distance - without sqrt
    val distance = (left.toList() zip right.toList()).sumOf { (l, r) -> (l - r) * (l - r) }
    return Edge(left, right, distance)
}

private fun parse(input: String): List<Vertex> =
    input.lines()
        .map { it.split(',') }
        .map { Vertex(it[0].toLong(), it[1].toLong(), it[2].toLong()) }
