package eu.tinylinden.aoc.y2025.d02

fun giftShopOne(input: String): Long =
    parse(input)
        .flatMap { range -> range.filter { invalidOne("$it") } }
        .sum()

fun giftShopTwo(input: String): Long =
    parse(input)
        .flatMap { range -> range.filter { invalidTwo("$it") } }
        .sum()

private fun invalidOne(s: String): Boolean =
    s.length % 2 == 0 && s.chunked(s.length / 2).let { it.first() == it.last() }

// slow but working
private fun invalidTwo(s: String): Boolean =
    generateSequence(1) { it + 1 }
        .take(s.length - 1)
        .any { s.chunked(it).distinct().size == 1 }

private fun parse(input: String): List<LongRange> =
    input.split(',')
        .map { it.split('-') }
        .map { it.first().toLong()..it.last().toLong() }