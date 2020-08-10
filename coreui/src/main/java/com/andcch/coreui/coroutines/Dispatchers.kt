package com.andcch.coreui.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers as CoroutineDispatchers

interface Dispatchers {

    val default: CoroutineDispatcher

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher
}

object DispatchersImpl : Dispatchers {

    override val default: CoroutineDispatcher = CoroutineDispatchers.Default

    override val main: CoroutineDispatcher = CoroutineDispatchers.Main

    override val io: CoroutineDispatcher = CoroutineDispatchers.IO
}
