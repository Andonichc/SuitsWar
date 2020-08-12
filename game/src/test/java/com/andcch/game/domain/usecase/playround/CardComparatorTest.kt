package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.CardFixtures
import com.andcch.game.domain.fixtures.SuitsPriorityFixtures
import com.andcch.game.domain.model.Card
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
abstract class CardComparatorTest : CardFixtures {

    @RunWith(Parameterized::class)
    class Compare(
        private val cards: List<Card>,
        private val suitsPriority: Map<Card.Suit, Int>,
        private val expectedWinnerCard: Card
    ) : CardComparatorTest() {

        private companion object : SuitsPriorityFixtures {

            @JvmStatic
            @Parameterized.Parameters(name = " {2}")
            fun data() =
                listOf(
                    // Should return card when list has just one card
                    arrayOf(
                        listOf(Card(Card.Value.N2, Card.Suit.CLUBS)),
                        anySuitsPriority,
                        Card(Card.Value.N2, Card.Suit.CLUBS)
                    ),
                    // Should return card with higher value when same suit
                    arrayOf(
                        listOf(
                            Card(Card.Value.N2, Card.Suit.CLUBS),
                            Card(Card.Value.N3, Card.Suit.CLUBS)
                        ),
                        anySuitsPriority,
                        Card(Card.Value.N3, Card.Suit.CLUBS)
                    ),
                    // Should return card with higher value when different suit
                    arrayOf(
                        listOf(
                            Card(Card.Value.K, Card.Suit.CLUBS),
                            Card(Card.Value.A, Card.Suit.HEARTS)
                        ),
                        anySuitsPriority,
                        Card(Card.Value.A, Card.Suit.HEARTS)
                    ),
                    // Should return card with higher value when more than 2
                    arrayOf(
                        listOf(
                            Card(Card.Value.N2, Card.Suit.CLUBS),
                            Card(Card.Value.J, Card.Suit.SPADES),
                            Card(Card.Value.N3, Card.Suit.HEARTS)
                        ),
                        anySuitsPriority,
                        Card(Card.Value.J, Card.Suit.SPADES)
                    ),
                    // Should return card with higher suit priority when all have the same value
                    arrayOf(
                        listOf(
                            Card(Card.Value.A, Card.Suit.CLUBS),
                            Card(Card.Value.A, Card.Suit.SPADES),
                            Card(Card.Value.A, Card.Suit.HEARTS),
                            Card(Card.Value.A, Card.Suit.DIAMONDS)
                        ),
                        anySuitsPriority,
                        Card(Card.Value.A, Card.Suit.SPADES)
                    )
                )
        }

        @Test
        fun `compare should return winner card`() {
            val comparator = buildComparator()

            val winnerCard = comparator.compare(cards, suitsPriority)

            assertThat(winnerCard).isEqualTo(expectedWinnerCard)
        }
    }

    @RunWith(JUnit4::class)
    class Errors : CardComparatorTest() {

        @Test(expected = IllegalArgumentException::class)
        fun `compare should throw exception when cards list is empty`() {
            val comparator = buildComparator()

            comparator.compare(emptyList(), emptyMap())
        }

        @Test(expected = IllegalStateException::class)
        fun `compare should throw exception when first card suit priority is not defined and both cards have same value`() {
            val comparator = buildComparator()

            comparator.compare(anySetOfAces, emptyMap())
        }

        @Test(expected = IllegalStateException::class)
        fun `compare should throw exception when second card suit priority is not defined and both cards have same value`() {
            val comparator = buildComparator()

            comparator.compare(anySetOfAces, mapOf(Card.Suit.CLUBS to 0))
        }
    }

    protected fun buildComparator(): CardComparator = CardComparator()
}
