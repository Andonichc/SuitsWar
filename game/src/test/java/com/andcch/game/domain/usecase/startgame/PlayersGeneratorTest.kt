package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.fixtures.CardFixtures
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlayersGeneratorTest : CardFixtures {

    @Mock
    private lateinit var pokerCardsSetGenerator: PokerCardsSetGenerator

    @Test
    fun `generatePlayers should return 2 players`() {
        val generator = buildGenerator()

        val players = generator.generatePlayers()

        assertThat(players).hasSize(2)
    }

    @Test
    fun `generatePlayers should return players with names`() {
        given(pokerCardsSetGenerator.generateCardsSet()).willReturn(anySetOfCards)
        val generator = buildGenerator()

        val players = generator.generatePlayers()

        assertThat(players[0].name).isEqualTo(anyPlayerName)
        assertThat(players[1].name).isEqualTo(anySecondPlayerName)
    }

    @Test
    fun `generatePlayers should give the same amount of cards to each player`() {
        given(pokerCardsSetGenerator.generateCardsSet()).willReturn(anySetOfCards)
        val generator = buildGenerator()

        val players = generator.generatePlayers()

        val expectedSize = anySetOfCards.size / 2
        assertThat(players[0].playablePile).hasSize(expectedSize)
        assertThat(players[1].playablePile).hasSize(expectedSize)
    }

    @Test
    fun `generatePlayers should return discard piles empty`() {
        given(pokerCardsSetGenerator.generateCardsSet()).willReturn(anySetOfCards)
        val generator = buildGenerator()

        val players = generator.generatePlayers()

        assertThat(players[0].discardPile).isEmpty()
        assertThat(players[1].discardPile).isEmpty()
    }

    private fun buildGenerator() = PlayersGenerator(pokerCardsSetGenerator)
}
