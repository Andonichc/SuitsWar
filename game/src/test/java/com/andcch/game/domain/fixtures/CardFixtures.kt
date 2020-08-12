package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Card

interface CardFixtures {

    val anySetOfCards: List<Card>
        get() = listOf(
            Card(value = Card.Value.N2, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N3, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N4, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N5, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N6, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N7, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N8, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N9, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N10, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.J, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.Q, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.K, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.A, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.N2, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N3, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N4, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N5, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N6, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N7, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N8, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N9, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N10, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.J, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.Q, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.K, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.A, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.N2, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N3, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N4, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N5, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N6, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N7, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N8, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N9, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N10, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.J, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.Q, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.K, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.A, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.N2, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N3, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N4, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N5, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N6, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N7, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N8, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N9, suit = Card.Suit.SPADES),
            Card(value = Card.Value.N10, suit = Card.Suit.SPADES),
            Card(value = Card.Value.J, suit = Card.Suit.SPADES),
            Card(value = Card.Value.Q, suit = Card.Suit.SPADES),
            Card(value = Card.Value.K, suit = Card.Suit.SPADES),
            Card(value = Card.Value.A, suit = Card.Suit.SPADES)
        )

    val anySetOfAces: List<Card>
        get() = listOf(
            Card(value = Card.Value.A, suit = Card.Suit.CLUBS),
            Card(value = Card.Value.A, suit = Card.Suit.DIAMONDS),
            Card(value = Card.Value.A, suit = Card.Suit.HEARTS),
            Card(value = Card.Value.A, suit = Card.Suit.SPADES)
        )
}
