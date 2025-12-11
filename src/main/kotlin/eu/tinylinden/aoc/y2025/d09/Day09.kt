package eu.tinylinden.aoc.y2025.d09

import eu.tinylinden.aoc.base.graphs.SimpleWeightedGraph
import org.locationtech.jts.geom.Polygonal
import org.locationtech.jts.geom.prep.PreparedPolygon
import org.locationtech.jts.io.WKTReader
import kotlin.math.abs
import kotlin.math.max

fun movieTheaterOne(input: String): Long {
    val graph = SimpleWeightedGraph(parse(input), area)
    return graph.edges.maxOf { it.third }
}

fun movieTheaterTwo(input: String): Long {
    val graph = SimpleWeightedGraph(parse(input), area)
    return redOrGreenOnly(graph)
}

private typealias Tile = Pair<Long, Long>

private fun redOrGreenOnly(graph: SimpleWeightedGraph<Tile, Long>, reader: WKTReader = WKTReader()): Long {
    val poly = (graph.vertices + graph.vertices.first())
        .joinToString(prefix = "POLYGON ((", postfix = "))") { (x, y) -> "$x $y" }
        .let { PreparedPolygon(reader.read(it) as Polygonal) }

    val idx = idx(graph)

    return graph.edges
        .parallelStream()
        .filter { (left, right) ->
            if (left in idx || right in idx) {
                val (xl, yl) = left
                val (xr, yr) = right
                val rect = reader.read("POLYGON (($xl $yl, $xl $yr, $xr $yr, $xr $yl, $xl $yl))")
                poly.contains(rect)
            } else {
                false
            }
        }
        .reduce(0L, { acc, (_, _, c) -> max(acc, c) }, { l, r -> max(l, r) })
}

// meaningless fun name, but should return set of tiles on two longest
// horizontal edges of polygon defined by graph vertices. why? because
// puzzle input, that's why
private fun idx(graph: SimpleWeightedGraph<Tile, *>): Set<Tile> {
    // also - example input starts with horizontal edge, and puzzle input
    // starts with vertical one
    val polyEdges =
        if (graph.vertices[0].first == graph.vertices[1].first) {
            graph.vertices.drop(1) + graph.vertices[0]
        } else {
            graph.vertices
        }

    return polyEdges.asSequence()
        .chunked(2)
        .sortedByDescending { abs(it[0].first - it[1].first) }
        .take(2)
        .flatten()
        .toSet()
}

private val area: (Tile, Tile) -> Long = { left, right ->
    val (lx, ly) = left
    val (rx, ry) = right
    (abs(lx - rx) + 1) * (abs(ly - ry) + 1)
}

private fun parse(input: String): List<Tile> =
    input.lines()
        .map { it.split(',') }
        .map { Tile(it[0].toLong(), it[1].toLong()) }
