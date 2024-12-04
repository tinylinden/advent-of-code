package eu.tinylinden.aoc.base

typealias Cell<T> = Pair<Point, T>
typealias CharCell = Cell<Char>

fun point(cell: Cell<*>): Point = cell.first
