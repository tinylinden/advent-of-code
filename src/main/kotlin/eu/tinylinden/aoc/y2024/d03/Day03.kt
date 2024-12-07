package eu.tinylinden.aoc.y2024.d03

// https://adventofcode.com/2024/day/3

fun mullItOver(s: String, extended: Boolean): Long =
    parse(s).fold(State(extended)) { state, token -> state.eval(token) }.result.toLong()

private fun parse(s: String): List<String> =
    s.lines().flatMap { line ->
        OPS.findAll(line).flatMap { it.groupValues }
    }

private class State(
    private val extended: Boolean
) {
    var enabled: Boolean = true
    var result: Int = 0

    fun eval(token: String): State {
        when {
            token.matches(OP_DONT) && extended -> enabled = false
            token.matches(OP_DO) && extended -> enabled = true
            token.matches(OP_MUL) && enabled -> eval(token) { first() * last() }
        }
        return this
    }

    private fun eval(token: String, op: Sequence<Int>.() -> Int) {
        result += op(opArgs(token))
    }

    private fun opArgs(token: String) =
        OP_ARGS.findAll(token).flatMap { it.groupValues }.map { it.toInt() }
}

private val OP_DONT = Regex("don't\\(\\)")
private val OP_DO = Regex("do\\(\\)")
private val OP_MUL = Regex("mul\\(\\d+,\\d+\\)")
private val OPS = Regex("$OP_DONT|$OP_DO|$OP_MUL")
private val OP_ARGS = Regex("\\d+")
