package com.andcch.game.domain.repository

import com.andcch.game.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getGameUpdates(): Flow<Game>

    fun saveGame(game: Game)

    fun ereaseGame()

    fun getGame(): Game?
}
