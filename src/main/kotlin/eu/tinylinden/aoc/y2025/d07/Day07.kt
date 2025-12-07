package eu.tinylinden.aoc.y2025.d07

fun laboratoriesOne(input: String): Long =
    parse(input).splits()

fun laboratoriesTwo(input: String): Long =
    parse(input).timelines()

private data class TachyonManifold(
    val source: Int,
    val splitters: List<Set<Int>>,
    val width: Int,
) {
    fun splits(): Long {
        tailrec fun part(beams: Set<Int>, splitters: List<Set<Int>>, acc: Long): Long =
            if (splitters.isEmpty()) {
                acc
            } else {
                val hits = beams intersect splitters.first()
                val split = hits.flatMap { listOf(it - 1, it + 1) }.toSet()

                part(split + (beams - hits), splitters.drop(1), acc + hits.size)
            }

        return part(setOf(source), splitters, 0)
    }

    fun timelines(): Long {
        val acc = LongArray(width).also { it[source] = 1 }
        splitters.forEach { line ->
            line.forEach { splitter ->
                acc[splitter - 1] += acc[splitter]
                acc[splitter + 1] += acc[splitter]
                acc[splitter] = 0
            }
        }
        return acc.sum()
    }
}

private fun parse(input: String): TachyonManifold {
    fun splitters(line: String): Set<Int>? =
        line.mapIndexedNotNull { i, c -> i.takeIf { c == '^' } }.takeIf { it.isNotEmpty() }?.toSet()

    val lines = input.lines()

    return TachyonManifold(
        source = lines.first().indexOf('S'),
        splitters = lines.drop(1).mapNotNull { splitters(it) },
        width = lines.first().length,
    )
}
