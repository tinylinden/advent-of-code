package eu.tinylinden.aoc.y2025.d04

fun printingDepartmentOne(input: String): Long =
    mask(parse(input)).flatten().count { it == 0 }.toLong()

fun printingDepartmentTwo(input: String): Long = 0

private typealias Grid = List<List<Int>>

private fun mask(grid: Grid): Grid =
    grid.mapIndexed { ri, row ->
        row.mapIndexed { ci, _ -> mask(grid, ri, ci) }
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

private fun parse(input: String): Grid =
    input.lines()
        .map { line ->
            line.toCharArray()
                .map { chr -> if (chr == '.') 0 else 1 }
        }
