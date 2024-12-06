package eu.tinylinden.aoc.base

interface Direction {
    val delta: Point
}

enum class CardinalDirection(override val delta: Point) : Direction {
    RIGHT(0 to 1),
    DOWN(1 to 0),
    LEFT(0 to -1),
    UP(-1 to 0),
    ;

    fun right(): CardinalDirection =
        when (this) {
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            UP -> RIGHT
        }
}

enum class OrdinalDirection(override val delta: Point) : Direction {
    RIGHT_DOWN(1 to 1),
    LEFT_DOWN(1 to -1),
    LEFT_UP(-1 to -1),
    RIGHT_UP(-1 to 1),
}

val DIRECTIONS_CARDINAL: List<CardinalDirection> = CardinalDirection.entries
val DIRECTIONS_ORDINAL: List<OrdinalDirection> = OrdinalDirection.entries
val DIRECTIONS_ALL: List<Direction> = DIRECTIONS_CARDINAL + DIRECTIONS_ORDINAL
