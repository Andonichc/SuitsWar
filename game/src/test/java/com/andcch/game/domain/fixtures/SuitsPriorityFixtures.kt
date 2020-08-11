package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Card

interface SuitsPriorityFixtures {

    val anySuitsPriority: Map<Card.Suit, Int>
        get() = mapOf(
            Card.Suit.CLUBS to 0,
            Card.Suit.DIAMONDS to 1,
            Card.Suit.HEARTS to 2,
            Card.Suit.SPADES to 3
        )
}
