package com.andcch.game.presentation.game.mapper

import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.PlayerStats
import com.andcch.game.presentation.game.model.PlayerViewModel
import javax.inject.Inject

// TODO Tests for this class are missing due to timing
class PlayerViewModelMapper @Inject constructor() {

    fun transform(player: Player): PlayerViewModel = PlayerViewModel(
        name = player.name,
        points = player.discardPile.size.toString()
    )

    fun transform(player: PlayerStats): PlayerViewModel = PlayerViewModel(
        name = player.name,
        points = player.points.toString()
    )
}
