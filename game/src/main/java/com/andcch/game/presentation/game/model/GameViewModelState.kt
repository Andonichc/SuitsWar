package com.andcch.game.presentation.game.model

data class GameViewModelState(
    val players: List<PlayerViewModel>,
    val rounds: List<RoundViewModel>,
    val gameStateText: String,
    val isPlayable: Boolean
)
