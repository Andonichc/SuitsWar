package com.andcch.game.presentation.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.presentation.game.model.PlayerViewModel

interface PlayerViewModelFixtures {

    val amyViewModelPlayers: List<PlayerViewModel>
        get() = listOf(
            PlayerViewModel(anyPlayerName, 11),
            PlayerViewModel(anyPlayerName, 7)
        )
}
