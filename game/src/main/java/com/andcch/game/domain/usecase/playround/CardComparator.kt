package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.model.Card
import javax.inject.Inject

class CardComparator @Inject constructor() {

    fun compare(cards: List<Card>, suitsPriority: Map<Card.Suit, Int>): Card {
        require(cards.isNotEmpty()) { "list of cards to compare cannot be empty" }

        return cards.reduce { card1, card2 ->
            when {
                card1.value > card2.value -> card1
                card1.value < card2.value -> card2
                suitsPriority.getOrThrow(card1.suit) > suitsPriority.getOrThrow(card2.suit) -> card1
                else -> card2
            }
        }
    }

    private fun Map<Card.Suit, Int>.getOrThrow(suit: Card.Suit): Int = get(suit)
        ?: throw IllegalStateException(
            "suitsPriority map should contain priority for suit: ${suit.name}"
        )
}
