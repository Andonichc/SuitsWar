package com.andcch.game.presentation.game.di

import com.andcch.game.presentation.game.GameActivity
import com.andcch.game.presentation.game.GamePresenter
import com.andcch.game.presentation.game.GamePresenterImpl
import com.andcch.game.presentation.game.GameView
import dagger.Binds
import dagger.Module

@Module
abstract class GameModule {

    @Binds
    abstract fun bindGameView(gameActivity: GameActivity): GameView

    @Binds
    abstract fun bindGamePresenter(gamePresenterImpl: GamePresenterImpl): GamePresenter
}
