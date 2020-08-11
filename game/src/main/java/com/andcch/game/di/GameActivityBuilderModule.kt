package com.andcch.game.di

import com.andcch.coreui.di.scope.ActivityScope
import com.andcch.game.presentation.game.GameActivity
import com.andcch.game.presentation.game.di.GameModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class GameActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [GameModule::class])
    abstract fun bindGameActivity(): GameActivity
}
