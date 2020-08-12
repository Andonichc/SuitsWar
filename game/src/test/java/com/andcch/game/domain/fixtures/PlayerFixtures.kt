package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Card
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
                playablePile = anySetOfCards.subList(0, anySetOfCards.size / 2)
            ),
            Player(
                name = anySecondPlayerName,
                playablePile = anySetOfCards.subList(anySetOfCards.size / 2, anySetOfCards.size)
            )
        )

    fun givenAPlayer(
        withName: String = anyPlayerName,
        withPlayablePile: List<Card> = anySetOfCards.subList(0, anySetOfCards.size / 2),
        withDiscardPile: List<Card> = emptyList()
    ): Player = Player(
        name = withName,
        playablePile = withPlayablePile,
        discardPile = withDiscardPile
    )
}
