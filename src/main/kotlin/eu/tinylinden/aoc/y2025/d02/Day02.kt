package eu.tinylinden.aoc.y2025.d02

fun giftShopOne(input: String): Long =
    parse(input)
        .flatMap { invalid(it) }
        .sum()

fun giftShopTwo(input: String): Long = 0

private fun invalid(range: LongRange): List<Long> {
    fun invalid(s: String): Boolean =
        s.length % 2 == 0 && s.chunked(s.length / 2).let { it.first() == it.last() }

    return range.filter { invalid("$it") }
}

private fun parse(input: String): List<LongRange> =
    input.split(',')
        .map { it.split('-') }
        .map { it.first().toLong()..it.last().toLong() }