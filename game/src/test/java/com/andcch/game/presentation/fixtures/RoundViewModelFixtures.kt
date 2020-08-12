package com.andcch.game.presentation.fixtures

import com.andcch.game.presentation.game.model.RoundViewModel

interface RoundViewModelFixtures {

    companion object {
        const val ANY_WINNER_TEXT = "Player 1 wins this round!"
        const val ANY_ROUND_TEXT = "Player 1 threw 5<3"
        const val ANY_OTHER_WINNER_TEXT = "Player 2 wins this round!"
        const val ANY_OTHER_ROUND_TEXT = "Player 2 threw J<3"
    }

    val anyViewModelRounds: List<RoundViewModel>
        get() = listOf(
            RoundViewModel(winnerText = ANY_WINNER_TEXT, roundText = ANY_ROUND_TEXT),
            RoundViewModel(winnerText = ANY_OTHER_WINNER_TEXT, roundText = ANY_OTHER_ROUND_TEXT)
        )
}
