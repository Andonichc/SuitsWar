package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.Player

interface PlayerFixtures : CardFixtures {

    companion object {
        const val ANY_PLAYER_NAME = "Player 1"
        const val ANY_SECOND_PLAYER_NAME = "Player 2"
    }

    val anyPlayers: List<Player>
        get() = listOf(
            Player(
                name = ANY_PLAYER_NAME,
                playablePile = anySetOfCards.subList(0, anySetOfCards.size / 2)
            ),
            Player(
                name = ANY_SECOND_PLAYER_NAME,
                playablePile = anySetOfCards.subList(anySetOfCards.size / 2, anySetOfCards.size)
            )
        )

    fun givenAPlayer(
        withName: String = ANY_PLAYER_NAME,
        withPlayablePile: List<Card> = anySetOfCards.subList(0, anySetOfCards.size / 2),
        withDiscardPile: List<Card> = emptyList()
    ): Player = Player(
        name = withName,
        playablePile = withPlayablePile,
        discardPile = withDiscardPile
    )
}
