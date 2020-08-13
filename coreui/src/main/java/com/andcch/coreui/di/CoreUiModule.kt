package com.andcch.coreui.di

import com.andcch.coreui.coroutines.Dispatchers
import com.andcch.coreui.coroutines.DispatchersImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreUiModule {

    @Singleton
    @Binds
    abstract fun bindDispatchers(dispatchersImpl: DispatchersImpl): Dispatchers
}
