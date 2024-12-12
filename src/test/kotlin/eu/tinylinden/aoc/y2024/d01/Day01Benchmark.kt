package eu.tinylinden.aoc.y2024.d01

import eu.tinylinden.aoc.JmhAware
import eu.tinylinden.aoc.pOneInput
import org.openjdk.jmh.annotations.Benchmark

internal open class Day01Benchmark :JmhAware(){

    @Benchmark
    fun partOne() {
        distance(
            pOneInput("2024", "01")
        )
    }

    @Benchmark
    fun partTwo() {
        similarity(
            pOneInput("2024", "01")
        )
    }
}