package eu.tinylinden.aoc.base

fun <T> parsePairs(input: String, regex: Regex, delimiter: Char, f: (String) -> T): List<Pair<T, T>> =
    input.lines()
        .filter { regex.matches(it) }
        .map { parsePair(it, delimiter, f) }

fun <T> parsePairs(input: String, regex: Regex, f: (String) -> T): List<Pair<T, T>> =
    input.lines()
        .filter { it.isNotBlank() }
        .map { line ->
            val match = regex.findAll(line)
            f(match.first().value) to f(match.last().value)
        }

private fun <T> parsePair(line: String, delimiter: Char, f: (String) -> T): Pair<T, T> =
    f(line.substringBefore(delimiter)) to f(line.substringAfter(delimiter))

fun <T> parseLists(input: String, regex: Regex, delimiter: Char, f: (String) -> T): List<List<T>> =
    input.lines()
        .filter { regex.matches(it) }
        .map { parseList(it, delimiter, f) }

private fun <T> parseList(line: String, delimiter: Char, f: (String) -> T): List<T> =
    line.split(delimiter).map(f)
