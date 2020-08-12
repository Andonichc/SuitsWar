package com.andcch.suitswar.di

import android.app.Application
import android.content.Context
import com.andcch.coreui.di.qualifier.ForApplication
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @ForApplication
    abstract fun bindAppContext(application: Application): Context
}
