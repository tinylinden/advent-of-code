package eu.tinylinden.aoc.base.graphs

class SimpleWeightedGraph<V, W>(
    val vertices: List<V>,
    weight: (V, V) -> W,
) {
    val edges: List<Triple<V, V, W>> by lazy {
        tailrec fun edges(idx: Int, acc: MutableList<Triple<V, V, W>>): List<Triple<V, V, W>> =
            if (idx == vertices.size) {
                acc
            } else {
                val left = vertices[idx]
                val part = vertices.subList(idx + 1, vertices.size)
                    .map { right ->
                        Triple(left, right, weight(left, right))
                    }
                acc.addAll(part)
                edges(idx + 1, acc)
            }

        edges(0, mutableListOf())
    }
}