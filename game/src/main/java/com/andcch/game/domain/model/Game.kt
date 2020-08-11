package com.andcch.game.domain.model

data class Game(
    val players: List<Player>,
    val suitsPriority: Map<Card.Suit, Int>
)
