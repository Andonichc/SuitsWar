package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_SECOND_PLAYER_NAME
import com.andcch.game.domain.model.PlayerStats

interface PlayerStatsFixtures {

    val anyPlayerStatsList: List<PlayerStats>
        get() = listOf(
            PlayerStats(name = ANY_PLAYER_NAME, points = 11),
            PlayerStats(name = ANY_SECOND_PLAYER_NAME, points = 7)
        )

    fun givenAPlayerStats(withName: String = ANY_PLAYER_NAME, withPoints: Int = 0) =
        PlayerStats(name = withName, points = withPoints)
}
