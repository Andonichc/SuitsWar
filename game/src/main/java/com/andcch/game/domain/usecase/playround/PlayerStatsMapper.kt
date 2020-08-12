package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.PlayerStats
import javax.inject.Inject

class PlayerStatsMapper @Inject constructor() {

    fun transform(players: List<Player>): List<PlayerStats> = players.map {
        PlayerStats(it.name, it.discardPile.size)
    }
}
