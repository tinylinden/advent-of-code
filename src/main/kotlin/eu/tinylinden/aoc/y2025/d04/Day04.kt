package eu.tinylinden.aoc.y2025.d04

fun printingDepartmentOne(input: String): Long {
    val grid = parse(input)
    val mask = mask(grid)
    val x = mask.flatMap { it.toList() }.count { it == 0 }
    return x.toLong()
}

fun printingDepartmentTwo(input: String): Long = 0

private typealias Grid = Array<IntArray>

private fun mask(grid: Grid): Grid {
    val cn = grid.mapIndexed { ri, row ->
        row.mapIndexed { ci, _ -> mask(grid, ri, ci) }
            .toIntArray()
    }
    return cn.toTypedArray()
}

private fun mask(grid: Grid, ri: Int, ci: Int, thr: Int = 4): Int =
    if (cell(grid, ri, ci) == 0) {
        1
    } else {
        // @formatter:off
        val adj = cell(grid, ri - 1, ci - 1) + // NW
                  cell(grid, ri - 1, ci    ) + // N
                  cell(grid, ri - 1, ci + 1) + // NE
                  cell(grid, ri    , ci + 1) + // E
                  cell(grid, ri + 1, ci + 1) + // SE
                  cell(grid, ri + 1, ci    ) + // S
                  cell(grid, ri + 1, ci - 1) + // SW
                  cell(grid, ri    , ci - 1)   // W
        // @formatter:on
        if (adj < thr) 0 else 1
    }

private fun cell(grid: Grid, ri: Int, ci: Int): Int =
    runCatching { grid[ri][ci] }.getOrDefault(0)

private fun parse(input: String): Grid {
    val parsed = input.lines()
        .map { line ->
            line.toCharArray()
                .map { chr -> if (chr == '.') 0 else 1 }
                .toIntArray()
        }
    return parsed.toTypedArray()
}