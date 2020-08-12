package com.andcch.game.presentation.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.presentation.game.model.PlayerViewModel

interface PlayerViewModelFixtures {

    val amyViewModelPlayers: List<PlayerViewModel>
        get() = listOf(
            PlayerViewModel(ANY_PLAYER_NAME, 11),
            PlayerViewModel(ANY_PLAYER_NAME, 7)
        )
}
