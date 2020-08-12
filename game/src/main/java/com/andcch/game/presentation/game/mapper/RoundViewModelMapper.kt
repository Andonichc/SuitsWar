package com.andcch.game.presentation.game.mapper

import android.content.Context
import com.andcch.coreui.di.qualifier.ForApplication
import com.andcch.game.R
import com.andcch.game.domain.model.PlayerCard
import com.andcch.game.domain.model.Round
import com.andcch.game.presentation.game.model.RoundViewModel

// TODO Tests for this class are missing due to timing
class RoundViewModelMapper constructor(
    @ForApplication private val context: Context,
    private val cardTextProvider: CardTextProvider
) {

    fun transform(round: Round): RoundViewModel = RoundViewModel(
        winnerText = context.getString(R.string.round_winner, round.winnerName),
        roundText = buildRoundText(round.playedCards)
    )

    private fun buildRoundText(playerStatsList: List<PlayerCard>): String =
        playerStatsList.joinToString(separator = "\n") { (playerName, card) ->
            context.getString(
                R.string.round_card_thrown,
                playerName,
                cardTextProvider.transform(card)
            )
        }
}
