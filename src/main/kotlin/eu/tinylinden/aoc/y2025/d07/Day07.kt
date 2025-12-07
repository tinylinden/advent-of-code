package eu.tinylinden.aoc.y2025.d07

fun laboratoriesOne(input: String): Long =
    parse(input).beams()

fun laboratoriesTwo(input: String): Long = 0

private data class TachyonManifold(
    val source: Int,
    val splitters: List<Set<Int>>,
) {
    fun beams(): Long {
        tailrec fun part(beams: Set<Int>, rest: List<Set<Int>>, acc: Long): Long =
            if (rest.isEmpty()) {
                acc
            } else {
                val curr = rest.first()
                val hits = beams intersect curr
                val split = hits.flatMap { listOf(it - 1, it + 1) }.toSet()

                part(split + (beams - curr), rest.drop(1), acc + hits.size)
            }

        return part(setOf(source), splitters, 0)
    }
}

private fun parse(input: String): TachyonManifold {
    fun splitters(line: String): Set<Int>? =
        line.mapIndexedNotNull { i, c -> i.takeIf { c == '^' } }.takeIf { it.isNotEmpty() }?.toSet()

    val lines = input.lines()

    return TachyonManifold(
        source = lines.first().indexOf('S'),
        splitters = lines.drop(1).mapNotNull { splitters(it) },
    )
}
