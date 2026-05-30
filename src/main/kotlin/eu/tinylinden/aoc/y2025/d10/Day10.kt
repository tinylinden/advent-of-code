package eu.tinylinden.aoc.y2025.d10

import com.google.common.collect.Sets.combinations

fun factoryOne(input: String): Long {
    return input.lines().sumOf { Machine(it).solveIndicators() }.toLong()
}

fun factoryTwo(input: String): Long {
    val machines = input.lines().map { Machine(it) }
    val m = machines.first()

    return input.lines().sumOf { Machine(it).solveJoltages() }.toLong()
}

private data class Machine(val raw: String) {
    val indicator: Indicator
    val buttons: Set<Button>
    val joltageLevels: JoltageLevels
    val combos: Map<Indicator, Int> // indicator -(after)-> number of button presses

    init {
        var buf = raw.split(" ").map { it.trimBrackets() }
        indicator = Indicator(buf.first())
        buttons = buf.subList(1, buf.size - 1).map { Button(it, buf[0].length) }.toSet()
        joltageLevels = JoltageLevels(buf.last())

        // warn: ugly as f...
        combos = (1..buttons.size)
            .flatMap { n ->
                combinations(buttons, n)
                    .map { combo ->
                        combo.fold(0) { l, r -> l xor r } to n
                    }
            }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.min() }
    }

    fun solveIndicators(): Int = combos[indicator]!!

    fun solveJoltages(): Int {
        fun part(curr: JoltageLevels): Int {
            val ind = curr.indicator()
            if (ind == 0) return 0
            return (combos[ind] ?: throw IllegalStateException("missing ind = $ind")) +
                    2 * part(curr.evened().halved())
        }
        return part(joltageLevels)
    }

    override fun toString() = raw
}

private typealias Indicator = Int

private fun Indicator(s: String): Indicator =
    s.replace('.', '0').replace('#', '1').toInt(2)

private typealias Button = Int

private fun Button(s: String, n: Int): Button {
    val buf = s.split(',').map { it.toInt() }.toSet()
    return List(n) { idx -> if (idx in buf) 1 else 0 }.joinToString("").toInt(2)
}

private typealias JoltageLevels = List<Int>

private fun JoltageLevels(s: String): JoltageLevels =
    s.split(',').map { it.toInt() }

// example: 3,5,4,7 -(odd = 1, even = 0)-> 1,1,0,1 -> 13
private fun JoltageLevels.indicator(): Indicator =
    joinToString("") { "${it % 2}" }.toInt(2)

// example: 3,5,4,7 -(down to nearest even)-> 2,4,4,6
private fun JoltageLevels.evened(): JoltageLevels =
    map { if (it % 2 == 0) it else it - 1 }

// example: 2,4,4,6 -> 1,2,2,3
private fun JoltageLevels.halved(): JoltageLevels =
    map { it / 2 }

private fun String.trimBrackets(): String =
    trim('[', ']', '(', ')', '{', '}')