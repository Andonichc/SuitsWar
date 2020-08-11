package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.model.Card
import javax.inject.Inject

class PokerCardsSetGenerator @Inject constructor() {

    fun generateCardsSet(): List<Card> =
        mutableListOf<Card>().apply {
            Card.Suit.values().flatMap { suit ->
                Card.Value.values().map { value ->
                    add(Card(value, suit))
                }
            }
        }
}
