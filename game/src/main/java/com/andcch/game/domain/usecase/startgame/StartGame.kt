package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.model.Game
import com.andcch.game.domain.repository.GameRepository
import javax.inject.Inject

class StartGame @Inject constructor(
    private val playersGenerator: PlayersGenerator,
    private val suitsPriorityGenerator: SuitsPriorityGenerator,
    private val gameRepository: GameRepository
) {

    fun execute() {
        gameRepository.saveGame(
            Game(
                players = playersGenerator.generatePlayers(),
                suitsPriority = suitsPriorityGenerator.generatePriority()
            )
        )
    }
}
