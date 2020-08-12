package com.andcch.game.presentation.game.mapper

import android.content.Context
import com.andcch.coreui.di.qualifier.ForApplication
import com.andcch.game.R
import com.andcch.game.domain.model.GameState
import com.andcch.game.presentation.game.model.GameViewModelState
import javax.inject.Inject

class GameViewModelStateMapper @Inject constructor(
    @ForApplication private val context: Context,
    private val playerViewModelMapper: PlayerViewModelMapper,
    private val roundViewModelMapper: RoundViewModelMapper
) {

    fun transform(gameState: GameState): GameViewModelState =
        when (gameState) {
            is GameState.Ongoing -> transformOngoingGame(gameState)
            is GameState.Finished -> transformFinishedGame(gameState)
        }

    private fun transformOngoingGame(gameState: GameState.Ongoing): GameViewModelState =
        GameViewModelState(
            players = gameState.players.map(playerViewModelMapper::transform),
            rounds = gameState.rounds.map(roundViewModelMapper::transform),
            gameStateText = getOnGoingGameStateText(gameState),
            isPlayable = true
        )

    private fun getOnGoingGameStateText(gameState: GameState.Ongoing): String =
        if (gameState.hasNotStarted()) {
            context.getString(R.string.game_state_start)
        } else {
            context.getString(
                R.string.game_state_round, gameState.rounds.last().winnerName
            )
        }

    private fun GameState.Ongoing.hasNotStarted() = rounds.isEmpty()

    private fun transformFinishedGame(gameState: GameState.Finished): GameViewModelState =
        GameViewModelState(
            players = gameState.playerStatsList.map(playerViewModelMapper::transform),
            rounds = gameState.rounds.map(roundViewModelMapper::transform),
            gameStateText = getFinishedGameStateText(gameState),
            isPlayable = false
        )

    private fun getFinishedGameStateText(gameState: GameState.Finished): String =
        when (gameState) {
            is GameState.Finished.WithWinner ->
                context.getString(R.string.game_state_winner, gameState.winnerName)
            is GameState.Finished.Tie ->
                context.getString(R.string.game_state_tie)
        }
}
