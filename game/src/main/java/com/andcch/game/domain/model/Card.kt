package com.andcch.game.domain.model

data class Card(val value: Value, val suit: Suit) {

    enum class Value {
        N2, N3, N4, N5, N6, N7, N8, N9, N10, J, Q, K, A;
    }

    enum class Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
}
