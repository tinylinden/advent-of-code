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

// ugly as hell, but working
fun diskFragmenterTwo(input: String): Long {
    val mem = input.foldIndexed(mutableListOf<Blk>()) { idx, acc, raw ->
        val cap = raw.digitToInt()
        when {
            idx % 2 == 0 -> acc += Blk.Full(cap, idx / 2)
            cap > 0 -> acc += Blk.Empty(cap)
        }
        acc
    }

    var r = mem.size - 1
    while (r > 0) {
        while (mem[r] !is Blk.Full) {
            r--
        }

        val sBlk = mem[r]
        val (tIdx, tBlk) = mem.withIndex().take(r).firstOrNull { (_, b) -> b is Blk.Empty && b.cap >= sBlk.cap }
            ?: IndexedValue(-1, Blk.Phony)

        if (tBlk is Blk.Empty) {
            if (tBlk.cap == sBlk.cap) {
                // just swap
                mem[r] = tBlk
                mem[tIdx] = sBlk
            }
            if (tBlk.cap > sBlk.cap) {
                // swap and insert
                mem[r] = Blk.Empty(sBlk.cap)
                mem[tIdx] = sBlk
                mem.add(tIdx + 1, Blk.Empty(tBlk.cap - sBlk.cap))
                r++
            }
        }
        r--
    }

    return mem.fold(0 to 0L) { (offset, acc), next -> (offset + next.cap) to acc + next.checksum(offset) }.second
}

private sealed interface Blk {
    val cap: Int get() = 0

    fun checksum(offset: Int): Long = 0

    data object Phony : Blk

    data class Empty(override val cap: Int) : Blk {
        override fun toString() = ".".repeat(cap)
    }

    data class Full(override val cap: Int, val dat: Int) : Blk {
        override fun toString() = "$dat".repeat(cap)
        override fun checksum(offset: Int): Long = (offset..<(offset + cap)).sumOf { (it * dat).toLong() }
    }
}
