package com.andcch.game.presentation.fixtures

import com.andcch.game.presentation.game.model.GameViewModelState

interface GameStateViewModelFixtures : PlayerViewModelFixtures, RoundViewModelFixtures {

    companion object {
        private const val ANY_GAME_STATE_TEXT = "Player 1 won this round"
    }

    val anyPlayableGameStateViewModel: GameViewModelState
        get() = GameViewModelState(
            players = anyViewModelPlayers,
            rounds = anyViewModelRounds,
            gameStateText = ANY_GAME_STATE_TEXT,
            isPlayable = true
        )

    val anyNonPlayableGameStateViewModel: GameViewModelState
        get() = GameViewModelState(
            players = anyViewModelPlayers,
            rounds = anyViewModelRounds,
            gameStateText = ANY_GAME_STATE_TEXT,
            isPlayable = false
        )
}
