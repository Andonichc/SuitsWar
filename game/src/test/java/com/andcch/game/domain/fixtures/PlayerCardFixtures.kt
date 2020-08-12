package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.PlayerCard

interface PlayerCardFixtures : CardFixtures {

    val anyPlayerCards: List<PlayerCard>
        get() = listOf(
            PlayerCard(playerName = anyPlayerName, card = anySetOfCards[0]),
            PlayerCard(playerName = anySecondPlayerName, card = anySetOfCards[1])
        )

    val anyOtherPlayerCards: List<PlayerCard>
        get() = listOf(
            PlayerCard(playerName = anyPlayerName, card = anySetOfCards[2]),
            PlayerCard(playerName = anySecondPlayerName, card = anySetOfCards[3])
        )

    fun givenAPlayerCard(
        withPlayerName: String = anyPlayerName,
        withCard: Card = anySetOfCards.first()
    ): PlayerCard = PlayerCard(playerName = withPlayerName, card = withCard)
}
