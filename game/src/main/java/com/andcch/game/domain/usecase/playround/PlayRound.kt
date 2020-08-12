package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.Player
import com.andcch.game.domain.repository.GameRepository
import javax.inject.Inject

class PlayRound @Inject constructor(
    private val gameRepository: GameRepository,
    private val cardComparator: CardComparator,
    private val gameRoundUpdater: GameRoundUpdater,
    private val finishedGameUpdater: FinishedGameUpdater
) {

    fun execute() {
        val currentGameState = gameRepository.getGame() as? GameState.Ongoing
            ?: throw IllegalStateException("Cannot play a round when there's no game going!")

        val cardsThrownByPlayer: Map<Card, Player> = currentGameState.throwCards()
        if (cardsThrownByPlayer.isEmpty()) {
            throw IllegalStateException("None of the players had cards to throw!")
        }

        val winnerCard: Card = cardComparator.compare(
            cardsThrownByPlayer.keys.toList(), currentGameState.suitsPriority
        )
        val winnerPlayer: Player = cardsThrownByPlayer[winnerCard]
            ?: throw IllegalStateException(
                "winner card must be tied to a player in cardsToPlay map"
            )

        val newGameState: GameState.Ongoing = gameRoundUpdater.updateGame(
            currentGameState, winnerPlayer, cardsThrownByPlayer
        )

        gameRepository.saveGame(
            if (newGameState.shouldContinue()) {
                newGameState
            } else {
                finishedGameUpdater.updateGame(newGameState)
            }
        )
    }

    private fun GameState.Ongoing.throwCards(): Map<Card, Player> =
        mutableMapOf<Card, Player>().apply {
            players.forEach { player ->
                if (player.playablePile.isNotEmpty()) {
                    put(player.throwCard(), player)
                }
            }
        }

    private fun GameState.Ongoing.shouldContinue(): Boolean =
        players.any { it.playablePile.isNotEmpty() }

    private fun Player.throwCard(): Card = this.playablePile[playablePile.lastIndex]
}
