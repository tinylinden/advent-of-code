package eu.tinylinden.aoc

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS, time = 5)
@Measurement(iterations = 5, timeUnit = TimeUnit.SECONDS, time = 5)
internal open class JmhAware
