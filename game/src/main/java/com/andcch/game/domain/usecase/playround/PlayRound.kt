package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.extension.dropLastElement
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.Round
import com.andcch.game.domain.repository.GameRepository
import javax.inject.Inject

class PlayRound @Inject constructor(
    private val gameRepository: GameRepository,
    private val cardComparator: CardComparator
) {

    fun execute() {
        val game = gameRepository.getGame() as? GameState.Ongoing
            ?: throw IllegalStateException("Cannot play a round when there's no game going!")

        val cardsThrownByPlayer: Map<Card, Player> = game.throwCards()

        val winnerCard: Card = cardComparator.compare(
            cardsThrownByPlayer.keys.toList(), game.suitsPriority
        )
        val winnerPlayer: Player = cardsThrownByPlayer[winnerCard]
            ?: throw IllegalStateException(
                "winner card must be tied to a player in cardsToPlay map"
            )

        updateGame(
            oldGame = game,
            winnerPlayer = winnerPlayer,
            cardsPlayed = cardsThrownByPlayer.keys.toList()
        )
    }

    private fun updateGame(
        oldGame: GameState.Ongoing,
        winnerPlayer: Player,
        cardsPlayed: List<Card>
    ) {
        val newGame: GameState = oldGame.copy(
            players = oldGame.players.map {
                it.copy(
                    playablePile = it.playablePile.dropLastElement(),
                    discardPile =
                    if (it == winnerPlayer) {
                        it.discardPile.plus(cardsPlayed)
                    } else {
                        it.discardPile
                    }
                )
            },
            rounds = oldGame.rounds.plus(
                Round(cardsPlayed, winnerPlayer.name)
            )
        )
        gameRepository.saveGame(newGame)
    }

    private fun GameState.Ongoing.throwCards(): Map<Card, Player> = mutableMapOf<Card, Player>().apply {
        players.forEach { player ->
            put(player.throwCard(), player)
        }
    }

    private fun Player.throwCard(): Card =
        if (this.playablePile.isNotEmpty()) {
            this.playablePile[playablePile.lastIndex]
        } else {
            throw IllegalStateException("Player: $name doesn't have more cards to play")
        }
}
