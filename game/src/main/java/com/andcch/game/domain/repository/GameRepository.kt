package com.andcch.game.domain.repository

import com.andcch.game.domain.model.GameState
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getGameUpdates(): Flow<GameState>

    fun saveGame(game: GameState)

    fun ereaseGame()

    fun getGame(): GameState?
}
