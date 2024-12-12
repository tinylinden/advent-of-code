package eu.tinylinden.aoc

import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue

internal interface JmhAbility {
    fun jmh() = Runner(
        OptionsBuilder()
            .include(this::class.java.simpleName)
            .mode(Mode.AverageTime)
            .warmupTime(TimeValue.seconds(1))
            .warmupIterations(6)
            .threads(1)
            .measurementIterations(6)
            .forks(1)
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .build()
    )
}