package com.andcch.coreui.arch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView {

    @Inject
    protected lateinit var presenter: P

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
