package eu.tinylinden.aoc.y2024.d05

// https://adventofcode.com/2024/day/5

fun printQueueOne(input: String): Int =
    rules(input).let { rules ->
        updates(input)
            .filter { isInOrder(it, rules) }
            .sumOf { it[it.size / 2] }
    }

fun printQueueTwo(input: String): Int =
    rules(input).let { rules ->
        updates(input)
            .filterNot { isInOrder(it, rules) }
            .map { fixOrder(it, rules) }
            .sumOf { it[it.size / 2] }
    }

private fun fixOrder(update: List<Int>, rules: Rules): List<Int> =
    update.sortedWith { l, r ->
        when {
            rules[l]?.contains(r) == true -> 1
            rules[r]?.contains(l) == true -> -1
            else -> 0
        }
    }

private fun isInOrder(pages: List<Int>, rules: Rules): Boolean =
    isInOrder(pages.first(), pages.drop(1), rules)

private fun isInOrder(page: Int, rest: List<Int>, rules: Rules): Boolean =
    when {
        rest.isEmpty() -> true
        rules[page]?.intersect(rest.toSet())?.isNotEmpty() == true -> false
        else -> isInOrder(rest.first(), rest.drop(1), rules)
    }

private fun rules(input: String): Rules {
    val buffer = mutableMapOf<Int, MutableSet<Int>>()
    input.lines().filter { it.contains('|') }
        .map { it.substringBefore('|').toInt() to it.substringAfter('|').toInt() }
        .forEach { (before, after) ->
            buffer.getOrPut(after) { mutableSetOf() } += before
        }
    return buffer
}

private fun updates(input: String): Updates =
    input.lines().filter { it.contains(',') }
        .map { line ->
            line.split(',').map { it.toInt() }
        }

private typealias Updates = List<List<Int>>
private typealias Rules = Map<Int, Set<Int>> // page -> after all of
