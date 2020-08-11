package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.model.Card
import javax.inject.Inject

class SuitsPriorityGenerator @Inject constructor() {

    fun generatePriority(): Map<Card.Suit, Int> = mutableMapOf<Card.Suit, Int>().apply {
        Card.Suit.values().toList().shuffled().forEachIndexed { index, suit ->
            put(suit, index)
        }
    }
}
