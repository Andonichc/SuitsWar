package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.andcch.game.domain.model.PlayerCard
import com.andcch.game.domain.model.Round

interface RoundFixtures : PlayerCardFixtures {

    val anyRounds: List<Round>
        get() = listOf(
            Round(anyPlayerCards, anyPlayerName),
            Round(anyOtherPlayerCards, anySecondPlayerName)
        )

    fun givenARound(
        withPlayedCards: List<PlayerCard> = anyPlayerCards,
        withWinnerName: String = anyPlayerName
    ): Round = Round(playedCards = withPlayedCards, winnerName = withWinnerName)
}
