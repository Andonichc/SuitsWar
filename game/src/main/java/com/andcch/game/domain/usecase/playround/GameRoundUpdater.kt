package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.extension.dropLastElement
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.PlayerCard
import com.andcch.game.domain.model.Round
import javax.inject.Inject

class GameRoundUpdater @Inject constructor() {

    fun updateGame(
        gameState: GameState.Ongoing,
        winnerPlayer: Player,
        playedCardsByPlayer: Map<Card, Player>
    ): GameState.Ongoing = gameState.copy(
        players = gameState.players.map {
            it.copy(
                playablePile = it.playablePile.dropLastElement(),
                discardPile =
                if (it == winnerPlayer) {
                    it.discardPile.plus(playedCardsByPlayer.keys)
                } else {
                    it.discardPile
                }
            )
        },
        rounds = gameState.rounds.plus(
            Round(
                playedCardsByPlayer.map { (card, player) ->
                    PlayerCard(player.name, card)
                },
                winnerPlayer.name
            )
        )
    )
}
