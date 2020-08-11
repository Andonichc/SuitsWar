package com.andcch.game.domain.model

data class Player(
    val name: String,
    val playablePile: List<Card>,
    val discardPile: List<Card> = emptyList()
)
