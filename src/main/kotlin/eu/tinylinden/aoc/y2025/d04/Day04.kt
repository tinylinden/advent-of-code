package eu.tinylinden.aoc.y2025.d04

fun printingDepartmentOne(input: String): Long =
    countZeros(mask(parse(input)))

fun printingDepartmentTwo(input: String): Long {
    tailrec fun part(grid: Grid, acc: Long): Long {
        val mask = mask(grid)
        val removable = countZeros(mask)
        return if (removable == 0L) {
            acc
        } else {
            part(grid * mask, acc + removable)
        }
    }

    return part(parse(input), 0)
}

private typealias Grid = List<List<Int>>

private operator fun Grid.times(other: Grid): Grid =
    this.mapIndexed { ri, row ->
        row.mapIndexed { ci, _ -> this[ri][ci] * other[ri][ci] }
    }

private fun countZeros(grid: Grid): Long =
    grid.flatten().count { it == 0 }.toLong()

private fun mask(grid: Grid): Grid =
    grid.mapIndexed { ri, row ->
        row.mapIndexed { ci, _ -> mask(grid, ri, ci) }
    }

private fun mask(grid: Grid, ri: Int, ci: Int, threshold: Int = 4): Int {
    fun adjacent(): Int =
        // @formatter:off
        cell(grid, ri - 1, ci - 1) + // NW
        cell(grid, ri - 1, ci    ) + // N
        cell(grid, ri - 1, ci + 1) + // NE
        cell(grid, ri    , ci + 1) + // E
        cell(grid, ri + 1, ci + 1) + // SE
        cell(grid, ri + 1, ci    ) + // S
        cell(grid, ri + 1, ci - 1) + // SW
        cell(grid, ri    , ci - 1)   // W
        // @formatter:on

    return when {
        cell(grid, ri, ci) == 0 -> -1 // empty position
        adjacent() < threshold -> 0   // roll at (ri, ci) is accessible on the grid
        else -> 1
    }
}

private fun cell(grid: Grid, ri: Int, ci: Int): Int =
    runCatching { grid[ri][ci] }.getOrDefault(0)

private fun parse(input: String): Grid =
    input.lines()
        .map { line ->
            line.toCharArray()
                .map { chr -> if (chr == '.') 0 else 1 }
        }
