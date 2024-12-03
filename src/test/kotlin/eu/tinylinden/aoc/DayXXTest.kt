package eu.tinylinden.aoc

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@Disabled
@DisplayName("")
internal class DayXXTest {

    @ParameterizedTest
    @MethodSource("testCases")
    fun test(case: String, tested: Tested) {
        runTest(case, tested)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            eOne("", "") { 0 },
            pOne("", "") { 0 },
            eTwo("", "") { 0 },
            pTwo("", "") { 0 },
        )
    }
}