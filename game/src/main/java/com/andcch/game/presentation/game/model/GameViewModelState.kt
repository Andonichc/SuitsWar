package com.andcch.game.presentation.game.model

data class GameViewModelState(
    val players: List<PlayerViewModel>,
    val rounds: List<RoundViewModel>,
    val gameStatusText: String,
    val isPlayable: Boolean
)
