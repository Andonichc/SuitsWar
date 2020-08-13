package com.andcch.suitswar.di

import android.app.Application
import com.andcch.coreui.di.CoreUiModule
import com.andcch.game.di.GameFeatureModule
import com.andcch.suitswar.SuitsWarApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, AndroidSupportInjectionModule::class, AppModule::class,
        GameFeatureModule::class, CoreUiModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: SuitsWarApp)
}
