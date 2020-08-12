package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.PlayerStats
import javax.inject.Inject

class FinishedGameUpdater @Inject constructor(private val playerStatsMapper: PlayerStatsMapper) {

    fun updateGame(gameState: GameState.Ongoing): GameState.Finished {
        val (players, _, rounds) = gameState

        val playerStatsList = playerStatsMapper.transform(players)

        val winnerName: String? = getWinnerName(playerStatsList)

        return if (winnerName != null) {
            GameState.Finished.WithWinner(winnerName, playerStatsList, rounds)
        } else {
            GameState.Finished.Tie(playerStatsList, rounds)
        }
    }

    private fun getWinnerName(playerStatsList: List<PlayerStats>): String? {
        require(playerStatsList.isNotEmpty()) { "There's no winner if nobody plays!" }
        val sortedPlayers = playerStatsList.sortedByDescending { it.points }
        return when {
            sortedPlayers.size == 1 -> sortedPlayers.first().name
            sortedPlayers[0].points == sortedPlayers[1].points -> null
            else -> sortedPlayers.first().name
        }
    }
}
