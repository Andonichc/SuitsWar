package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_SECOND_PLAYER_NAME
import com.andcch.game.domain.model.PlayerCard
import com.andcch.game.domain.model.Round

interface RoundFixtures : PlayerCardFixtures {

    val anyRounds: List<Round>
        get() = listOf(
            Round(anyPlayerCards, ANY_PLAYER_NAME),
            Round(anyOtherPlayerCards, ANY_SECOND_PLAYER_NAME)
        )

    fun givenARound(
        withPlayedCards: List<PlayerCard> = anyPlayerCards,
        withWinnerName: String = ANY_PLAYER_NAME
    ): Round = Round(playedCards = withPlayedCards, winnerName = withWinnerName)
}
