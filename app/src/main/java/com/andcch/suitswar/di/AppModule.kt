package com.andcch.suitswar.di

import android.app.Application
import android.content.Context
import com.andcch.coreui.di.qualifier.ForApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @ForApplication
    @Binds
    abstract fun bindAppContext(application: Application): Context
}
