package eu.tinylinden.aoc.y2024.d03

fun mullItOver(s: String, extended: Boolean): Int =
    parseOps(s).fold(State(extended)) { state, op -> state.eval(op) }.result

private fun parseOps(s: String): List<String> =
    s.lines().flatMap { line ->
        OP.findAll(line).flatMap { it.groupValues }
    }.also { println(it) }

private class State(
    private val extended: Boolean
) {
    var enabled: Boolean = true
    var result: Int = 0

    fun eval(op: String): State {
        when {
            op == "don't()" && extended -> enabled = false
            op == "do()" && extended -> enabled = true
            op.startsWith("mul") && enabled -> eval(op) { first() * last() }
        }
        return this
    }

    private fun eval(op: String, f: Sequence<Int>.() -> Int) {
        result += args(op).let(f)
    }

    private fun args(op: String) =
        OP_ARGS.findAll(op).flatMap { it.groupValues }.map { it.toInt() }
}

private val OP = Regex("do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)")
private val OP_ARGS = Regex("\\d+")
