package eu.tinylinden.aoc.y2025.d02

fun giftShopOne(input: String): Long =
    parse(input)
        .flatMap { range -> range.filter { invalidOne("$it") } }
        .sum()

fun giftShopTwo(input: String): Long =
    parse(input)
        .flatMap { range -> range.filter { invalidTwo("$it") } }
        .sum()

private fun invalidOne(s: String, n: Int = s.length): Boolean =
    n % 2 == 0 && s.take(n / 2) == s.drop(n / 2)

private fun invalidTwo(s: String): Boolean =
    (1..s.length / 2)
        .map { s.take(it).repeat(s.length / it) }
        .any { it == s }

private fun parse(input: String): List<LongRange> =
    input.split(',')
        .map { it.split('-') }
        .map { it.first().toLong()..it.last().toLong() }