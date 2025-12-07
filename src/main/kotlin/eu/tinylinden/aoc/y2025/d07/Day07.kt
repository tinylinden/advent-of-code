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
        tailrec fun part(
            splitters: List<Set<Int>>,
            acc: LongArray, // accumulate beams on given index
        ): LongArray =
            if (splitters.isEmpty()) {
                acc
            } else {
                splitters.first().forEach {
                    acc[it - 1] += acc[it]
                    acc[it + 1] += acc[it]
                    acc[it] = 0
                }

                part(splitters.drop(1), acc)
            }

        return part(splitters, LongArray(width).also { it[source] = 1 }).sum()
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
