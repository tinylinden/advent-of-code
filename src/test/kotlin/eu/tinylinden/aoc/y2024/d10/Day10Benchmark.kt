package eu.tinylinden.aoc.y2024.d10

import eu.tinylinden.aoc.JmhAware
import eu.tinylinden.aoc.pOneInput
import org.openjdk.jmh.annotations.Benchmark

internal open class Day10Benchmark : JmhAware() {

    @Benchmark
    fun partOne() {
        hoofItOne(
            pOneInput("2024", "10")
        )
    }

    @Benchmark
    fun partTwo() {
        hoofItTwo(
            pOneInput("2024", "10")
        )
    }
}