package eu.tinylinden.aoc.base

fun <T> List<T>.rest(): List<T> = drop(1)

fun <T> List<T>.middle(): T = this[size / 2]
