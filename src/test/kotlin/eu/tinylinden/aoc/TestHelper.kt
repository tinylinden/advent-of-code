package eu.tinylinden.aoc

import org.junit.jupiter.api.Assumptions.assumeTrue
import java.nio.charset.StandardCharsets

internal object TestHelper {
    fun testCase(case: String): Pair<String, Int> =
        try {
            this::class.java.getResource("/private/$case")
                .readText(StandardCharsets.UTF_8)
                .let {
                    it.substringAfter("\n") to it.substringBefore("\n").toInt()
                }
        } catch(ex: Exception) {
            assumeTrue(false)
            "" to 0 // irrelevant as
        }
}
