package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.andcch.game.domain.model.PlayerStats

interface PlayerStatsFixtures {

    val anyPlayerStatsList: List<PlayerStats>
        get() = listOf(
            PlayerStats(name = anyPlayerName, points = 11),
            PlayerStats(name = anySecondPlayerName, points = 7)
        )

    fun givenAPlayerStats(withName: String = anyPlayerName, withPoints: Int = 0) =
        PlayerStats(name = withName, points = withPoints)
}
