package com.andcch.coreui.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers as CoroutineDispatchers

interface Dispatchers {

    val default: CoroutineDispatcher

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher
}

class DispatchersImpl @Inject constructor() : Dispatchers {

    override val default: CoroutineDispatcher = CoroutineDispatchers.Default

    override val main: CoroutineDispatcher = CoroutineDispatchers.Main

    override val io: CoroutineDispatcher = CoroutineDispatchers.IO
}
