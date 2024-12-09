package eu.tinylinden.aoc.y2024.d09

fun diskFragmenterOne(input: String): Long =
    arrange(parse(input).toMutableList())
        .mapIndexed { i, v -> i * v }
        .sum()

fun diskFragmenterTwo(input: String): Long = 0

private fun arrange(buf: MutableList<Long?>): List<Long> {
    var l = 0
    var r = buf.size - 1

    while (l < r) {
        when {
            buf[l] != null -> l++
            buf[r] == null -> r--
            else -> {
                buf[l++] = buf[r]
                buf[r--] = null
            }
        }
    }

    return buf.filterNotNull()
}

private fun parse(input: String): List<Long?> =
    "${input}0".chunked(2)
        .flatMapIndexed { i, s ->
            List(s[0].digitToInt()) { i.toLong() } + List(s[1].digitToInt()) { null }
        }
