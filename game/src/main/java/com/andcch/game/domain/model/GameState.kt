package com.andcch.game.domain.model

sealed class GameState {

    data class Ongoing(
        val players: List<Player>,
        val suitsPriority: Map<Card.Suit, Int>,
        val rounds: List<Round> = emptyList()
    ) : GameState()

    sealed class Finished(
        open val playerStatsList: List<PlayerStats>,
        open val rounds: List<Round>
    ) : GameState() {

        data class Tie(
            override val playerStatsList: List<PlayerStats>,
            override val rounds: List<Round>
        ) : Finished(playerStatsList, rounds)

        data class WithWinner(
            val winnerName: String,
            override val playerStatsList: List<PlayerStats>,
            override val rounds: List<Round>
        ) : Finished(playerStatsList, rounds)
    }
}
