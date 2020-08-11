package com.andcch.game.data.cache

import kotlinx.coroutines.flow.Flow

interface ObservableItemCache<T> {

    fun updateFlow(): Flow<T>

    fun put(item: T)

    fun get(): T?

    fun clear()
}
