package eu.tinylinden.aoc.y2024.d09

// https://adventofcode.com/2024/day/9

// ugly as hell, but working
fun diskFragmenterOne(input: String): Long {
    val mem = input.foldIndexed(mutableListOf<Int?>()) { idx, acc, raw ->
        val cap = raw.digitToInt()
        when {
            idx % 2 == 0 -> acc += List(cap) { idx / 2 }
            cap > 0 -> acc += List(cap) { null }
        }
        acc
    }

    var l = 0
    var r = mem.size - 1

    while (l < r) {
        when {
            mem[l] != null -> l++
            mem[r] == null -> r--
            else -> {
                mem[l++] = mem[r]
                mem[r--] = null
            }
        }
    }

    return mem.foldIndexed(0L) { idx, acc, next -> acc + idx * (next ?: 0) }
}
