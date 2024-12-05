package eu.tinylinden.aoc.base

fun <T> parsePairs(input: String, regex: Regex, delimiter: Char, f: (String) -> T): List<Pair<T, T>> =
    input.lines()
        .filter { regex.matches(it) }
        .map { parsePair(it, delimiter, f) }

private fun <T> parsePair(line: String, delimiter: Char, f: (String) -> T): Pair<T, T> =
    f(line.substringBefore(delimiter)) to f(line.substringAfter(delimiter))

fun <T> parseLists(input: String, regex: Regex, delimiter: Char, f: (String) -> T): List<List<T>> =
    input.lines()
        .filter { regex.matches(it) }
        .map { parseList(it, delimiter, f) }

private fun <T> parseList(line: String, delimiter: Char, f: (String) -> T): List<T> =
    line.split(delimiter).map(f)
