package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.Round

interface RoundFixtures : CardFixtures {

    val anyRounds: List<Round>
        get() = listOf(
            Round(anySetOfCards.take(2), anyPlayerName),
            Round(anySetOfCards.takeLast(2), anySecondPlayerName)
        )

    fun givenARound(
        withPlayedCards: List<Card> = anySetOfCards.take(2),
        withWinnerName: String = anyPlayerName
    ) = Round(playedCards = withPlayedCards, winnerName = withWinnerName)
}
