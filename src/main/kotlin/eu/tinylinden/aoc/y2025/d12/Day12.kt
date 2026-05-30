package eu.tinylinden.aoc.y2025.d12

fun xmasTreeFarmOne(input: String): Long {
    val regions = parse(input)
    return regions.filterNot { it.cellsAvailable < it.cellsRequired }.count().toLong()
}

fun xmasTreeFarmTwo(input: String): Long = 0

data class Present(
    val cells: List<Int>,
) {
    val cellsRequired: Int = cells.sum()
}

data class Region(
    val width: Int,
    val height: Int,
    val presents: Map<Present, Int>,
) {
    val cellsAvailable: Int = width * height
    val cellsRequired: Int = presents.map { (present, count) -> count * present.cellsRequired }.sum()
}

// warn: ugly as f...
private fun parse(input: String): List<Region> {
    fun present(s: List<String>): Present =
        Present(s.flatMap { line -> line.toList().map { ch -> if (ch == '#') 1 else 0 } })

    val presents = input.lines().take(30).chunked(5) { present(it.subList(1, 4)) }

    fun region(s: List<String>): Region =
        s.map { it.toInt() }.let { r ->
            Region(
                width = r[0],
                height = r[1],
                presents = r.drop(2).mapIndexed { idx, count -> presents[idx] to count }.toMap()
            )
        }

    val regions = input.lines().drop(30).map { it.split(Regex("x|: | ")) }.map { region(it) }

    return regions
}
