package com.andcch.game.data.repository

import com.andcch.game.data.cache.ObservableItemCache
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameCache: ObservableItemCache<GameState>
) : GameRepository {

    override fun getGameUpdates(): Flow<GameState> = gameCache.updateFlow()

    override fun saveGame(game: GameState) {
        gameCache.put(game)
    }

    override fun getGame(): GameState? = gameCache.get()

    override fun ereaseGame() {
        gameCache.clear()
    }
}
