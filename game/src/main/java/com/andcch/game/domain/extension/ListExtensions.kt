package com.andcch.game.domain.extension

/**
 * Returns a new list without the last element
 */

fun <E> List<E>.dropLastElement(): List<E> = dropLast(1)
