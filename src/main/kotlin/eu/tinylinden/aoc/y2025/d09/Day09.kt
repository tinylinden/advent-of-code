package eu.tinylinden.aoc.y2025.d09

import eu.tinylinden.aoc.base.graphs.SimpleWeightedGraph
import org.locationtech.jts.geom.Geometry
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
private typealias Rectangle = Triple<Tile, Tile, Long>

// no time to invent 2d geometry
private fun redOrGreenOnly(graph: SimpleWeightedGraph<Tile, Long>, reader: WKTReader = WKTReader()): Long {
    fun geometry(rectangle: Rectangle): Geometry {
        val (xl, yl) = rectangle.first
        val (xr, yr) = rectangle.second
        return reader.read("POLYGON (($xl $yl, $xl $yr, $xr $yr, $xr $yl, $xl $yl))")
    }

    val polygon = (graph.vertices + graph.vertices.first())
        .joinToString(prefix = "POLYGON ((", postfix = "))") { (x, y) -> "$x $y" }
        .let { reader.read(it) }

    return graph.edges
        .parallelStream()
        .filter { polygon.covers(geometry(it)) }
        .reduce(0L, { acc, (_, _, c) -> max(acc, c) }, { l, r -> max(l, r) })
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
