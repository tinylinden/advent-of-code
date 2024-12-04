package eu.tinylinden.aoc.base

enum class Direction(val point: Point) {
    RIGHT(1 to 0),
    RIGHT_DOWN(1 to 1),
    DOWN(0 to 1),
    LEFT_DOWN(-1 to -1),
    LEFT(-1 to 0),
    LEFT_UP(-1 to 1),
    UP(0 to -1),
    RIGHT_UP(1 to -1),
}

val DIRECTIONS_CARDINAL = listOf(Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP)
val DIRECTIONS_ORDINAL = listOf(Direction.RIGHT_DOWN, Direction.LEFT_DOWN, Direction.LEFT_UP, Direction.RIGHT_UP)
val DIRECTIONS_ALL = DIRECTIONS_CARDINAL + DIRECTIONS_ORDINAL
