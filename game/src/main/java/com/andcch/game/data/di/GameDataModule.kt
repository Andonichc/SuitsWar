package com.andcch.game.data.di

import com.andcch.game.data.cache.InMemoryObservableItemCache
import com.andcch.game.data.cache.ObservableItemCache
import com.andcch.game.data.repository.GameRepositoryImpl
import com.andcch.game.domain.model.Game
import com.andcch.game.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class GameDataModule {

    @Binds
    abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    companion object {

        @Provides
        @Singleton
        fun provideGameCache(): ObservableItemCache<Game> = InMemoryObservableItemCache()
    }
}
