package com.andcch.game.data.repository

import com.andcch.game.data.cache.ObservableItemCache
import com.andcch.game.domain.model.Game
import com.andcch.game.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameCache: ObservableItemCache<Game>
) : GameRepository {

    override fun getGameUpdates(): Flow<Game> = gameCache.updateFlow()

    override fun saveGame(game: Game) {
        gameCache.put(game)
    }

    override fun ereaseGame() {
        gameCache.clear()
    }
}
