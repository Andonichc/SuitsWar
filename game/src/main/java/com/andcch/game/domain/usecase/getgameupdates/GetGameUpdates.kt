package com.andcch.game.domain.usecase.getgameupdates

import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUpdates @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): Flow<GameState> = gameRepository.getGameUpdates()
}
