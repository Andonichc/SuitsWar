package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_SECOND_PLAYER_NAME
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.PlayerCard

interface PlayerCardFixtures : CardFixtures {

    val anyPlayerCards: List<PlayerCard>
        get() = listOf(
            PlayerCard(playerName = ANY_PLAYER_NAME, card = anySetOfCards[0]),
            PlayerCard(playerName = ANY_SECOND_PLAYER_NAME, card = anySetOfCards[1])
        )

    val anyOtherPlayerCards: List<PlayerCard>
        get() = listOf(
            PlayerCard(playerName = ANY_PLAYER_NAME, card = anySetOfCards[2]),
            PlayerCard(playerName = ANY_SECOND_PLAYER_NAME, card = anySetOfCards[3])
        )

    fun givenAPlayerCard(
        withPlayerName: String = ANY_PLAYER_NAME,
        withCard: Card = anySetOfCards.first()
    ): PlayerCard = PlayerCard(playerName = withPlayerName, card = withCard)
}
