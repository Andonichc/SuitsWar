package com.andcch.game.domain.usecase.getsavedgame

import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.repository.GameRepository
import javax.inject.Inject

class GetSavedGame @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): GameState? = gameRepository.getGame()
}
