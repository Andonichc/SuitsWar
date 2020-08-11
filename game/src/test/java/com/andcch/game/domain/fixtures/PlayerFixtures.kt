package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Player

interface PlayerFixtures : CardFixtures {

    companion object {
        const val anyPlayerName = "Player 1"
        const val anySecondPlayerName = "Player 2"
    }

    val anyPlayers: List<Player>
        get() = listOf(
            Player(
                name = anyPlayerName,
                playablePile = anySetOfCards
                    .slice(0 until (anySetOfCards.size / 2))
            ),
            Player(
                name = anySecondPlayerName,
                playablePile = anySetOfCards
                    .slice((anySetOfCards.size / 2) until anySetOfCards.size)
            )
        )
}
