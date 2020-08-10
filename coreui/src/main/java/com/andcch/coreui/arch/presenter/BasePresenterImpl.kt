package com.andcch.coreui.arch.presenter

import com.andcch.coreui.arch.view.BasePresenter
import com.andcch.coreui.arch.view.BaseView
import com.andcch.coreui.coroutines.Dispatchers
import com.andcch.coreui.coroutines.DispatchersImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BasePresenterImpl<V : BaseView>(
    protected val view: V,
    protected val dispatchers: Dispatchers = DispatchersImpl
) : BasePresenter, CoroutineScope {

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext = job + dispatchers.main

    override fun onDestroy() {
        job.cancel()
    }

    protected fun runJob(
        onError: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            onError?.invoke(throwable)
        }

        launch(coroutineContext + errorHandler) {
            block()
        }
    }
}
