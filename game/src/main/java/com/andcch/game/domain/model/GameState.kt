package com.andcch.game.domain.model

sealed class GameState {
    data class Ongoing(
        val players: List<Player>,
        val suitsPriority: Map<Card.Suit, Int>,
        val rounds: List<Round> = emptyList()
    ) : GameState()

    data class Finished(
        val winnerName: String,
        val rounds: List<Round>
    ) : GameState()
}
