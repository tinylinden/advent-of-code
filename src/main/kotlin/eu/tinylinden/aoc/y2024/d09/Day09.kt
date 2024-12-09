package eu.tinylinden.aoc.y2024.d09

// https://adventofcode.com/2024/day/9

fun diskFragmenterOne(input: String): Long =
    moveBlocks(parse(input) as MutableList)
        .mapIndexed { idx, id -> (idx * (id ?: 0)).toLong() }
        .sum()

fun diskFragmenterTwo(input: String): Long =
    moveFiles(parse(input) as MutableList)
        .mapIndexed { idx, id -> (idx * (id ?: 0)).toLong() }
        .sum()

private fun moveBlocks(disk: MutableList<Int?>): List<Int?> {
    var l = 0
    var r = disk.size - 1

    while (l < r) {
        when {
            disk[l] != null -> l++
            disk[r] == null -> r--
            else -> {
                disk[l++] = disk[r]
                disk[r--] = null
            }
        }
    }

    return disk
}

private fun moveFiles(disk: MutableList<Int?>): List<Int?> {
    var hwm = disk.size - 1

    fun src(v: Int): MutableList<Int?> {
        var r = hwm
        while (disk[r] != v) {
            r--
        }

        var l = r
        while (disk[l - 1] == disk[r]) {
            l--
        }

        hwm = l - 1
        return disk.subList(l, r + 1)
    }

    fun move(src: MutableList<Int?>) {
        var l = 0
        val n = src.size
        while (l + n < hwm) {
            val target = disk.subList(l, l + n)
            if (target.all { it == null }) {
                target.fill(src[0])
                src.fill(null)
                return
            }
            l++
        }
    }

    println(disk.dump().take(100))
    println(disk.dump().takeLast(100))

    var i = disk.maxOf { it ?: -1 }
    while (i > 0) {
        move(src(i))
        i--
    }

    return disk
}

private fun List<Int?>.dump(): String = joinToString("") { it?.toString() ?: "." }

private fun parse(input: String): List<Int?> =
    "${input}0".chunked(2)
        .flatMapIndexed { i, s ->
            List(s[0].digitToInt()) { i } + List(s[1].digitToInt()) { null }
        }
