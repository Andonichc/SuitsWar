package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.model.Card
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SuitsPriorityGeneratorTest {

    @Test
    fun `generatePriority should return a map with four entries`() {
        val generator = buildGenerator()

        val suitsPriority = generator.generatePriority()

        assertThat(suitsPriority).hasSize(4)
    }

    @Test
    fun `generatePriority should return a map with an entry for each of the suits`() {
        val generator = buildGenerator()

        val suitsPriority = generator.generatePriority()

        assertThat(suitsPriority[Card.Suit.CLUBS]).isNotNull()
        assertThat(suitsPriority[Card.Suit.DIAMONDS]).isNotNull()
        assertThat(suitsPriority[Card.Suit.HEARTS]).isNotNull()
        assertThat(suitsPriority[Card.Suit.SPADES]).isNotNull()
    }

    @Test
    fun `generatePriority should return a different priority for each suit`() {
        val generator = buildGenerator()

        val suitsPriority = generator.generatePriority()

        assertThat(suitsPriority[Card.Suit.CLUBS])
            .isNotIn(suitsPriority.minus(Card.Suit.CLUBS).values)
        assertThat(suitsPriority[Card.Suit.DIAMONDS])
            .isNotIn(suitsPriority.minus(Card.Suit.DIAMONDS).values)
        assertThat(suitsPriority[Card.Suit.HEARTS])
            .isNotIn(suitsPriority.minus(Card.Suit.HEARTS).values)
        assertThat(suitsPriority[Card.Suit.SPADES])
            .isNotIn(suitsPriority.minus(Card.Suit.SPADES).values)
    }

    private fun buildGenerator() = SuitsPriorityGenerator()
}
