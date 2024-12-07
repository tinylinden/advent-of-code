package eu.tinylinden.aoc.base

fun <T> List<T>.rest(): List<T> = drop(1)

fun <T> List<T>.middle(): T = this[size / 2]

fun <T> List<T>.prepend(item: T): List<T> =
    buildList(size + 1) {
        add(item)
        addAll(this@prepend)
    }
