package eu.tinylinden.aoc

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 5)
@Measurement(iterations = 5, timeUnit = TimeUnit.SECONDS, time = 5)
internal open class JmhAware {

    fun main(args: Array<String>) {
        Runner(
            OptionsBuilder()
                .include(this::class.java.simpleName)
                .build()
        )
            .run()
    }
}