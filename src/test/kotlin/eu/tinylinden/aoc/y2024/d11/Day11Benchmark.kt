package eu.tinylinden.aoc.y2024.d11

import eu.tinylinden.aoc.JmhAware
import eu.tinylinden.aoc.pOneInput
import org.openjdk.jmh.annotations.Benchmark

internal open class Day11Benchmark : JmhAware() {

    @Benchmark
    fun partOne() {
        plutonianPebblesOne(
            pOneInput("2024", "11")
        )
    }
}