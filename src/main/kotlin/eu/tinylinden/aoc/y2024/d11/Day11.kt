package eu.tinylinden.aoc.y2024.d11

// https://adventofcode.com/2024/day/11

fun plutonianPebblesOne(input: String): Long {
    val cache: Cache = mutableMapOf()
    return stones(input).sumOf { blink(it, 25, cache) }
}

fun plutonianPebblesTwo(input: String): Long {
    val cache: Cache = mutableMapOf()
    return stones(input).sumOf { blink(it, 75, cache) }
}

private fun blink(stone: Long, n: Int, cache: Cache): Long {
    fun blink(stones: List<Long>): Long {
        val res = stones.sumOf { blink(it, n - 1, cache) }
        cache[stone to n] = res
        return res
    }

    return if (n == 0) {
        1
    } else {
        cache[stone to n] ?: blink(next(stone))
    }
}

private typealias Cache = MutableMap<Pair<Long, Int>, Long>

private fun next(stone: Long): List<Long> =
    when {
        stone == 0L -> listOf(1)
        splittable(stone) -> split(stone)
        else -> listOf(stone * 2024)
    }

private fun splittable(stone: Long): Boolean = "$stone".length % 2 == 0

private fun split(stone: Long): List<Long> {
    val str = "$stone"
    val len = str.length / 2
    return listOf(str.take(len).toLong(), str.drop(len).toLong())
}

private fun stones(input: String) = input.split(" ").map { it.toLong() }
