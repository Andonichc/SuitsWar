package com.andcch.game.data.cache

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class InMemoryObservableItemCache<T> @Inject constructor() : ObservableItemCache<T> {

    private val updateChanel = Channel<T>()
    private var cachedItem: T? = null

    override fun updateFlow(): Flow<T> = updateChanel.receiveAsFlow()

    override fun put(item: T) {
        cachedItem = item
        updateChanel.offer(item)
    }

    override fun get(): T? = cachedItem

    override fun clear() {
        cachedItem = null
    }
}
