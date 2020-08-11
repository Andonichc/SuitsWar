package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.fixtures.CardFixtures
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PokerCardsSetGeneratorTest : CardFixtures {

    @Test
    fun `generateCardsSet should return a set of poker cards`() {
        val generator = buildGenerator()

        val cardsSet = generator.generateCardsSet()

        assertThat(cardsSet).hasSize(52)
        assertThat(cardsSet).isEqualTo(anySetOfCards)
    }

    private fun buildGenerator() = PokerCardsSetGenerator()
}
