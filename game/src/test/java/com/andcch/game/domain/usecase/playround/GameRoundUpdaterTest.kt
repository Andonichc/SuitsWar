package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.GameStateFixtures
import com.andcch.game.domain.model.Round
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GameRoundUpdaterTest : GameStateFixtures {

    @Test
    fun `updateGame should update game without the last card of players playable pile`() {
        val playedCards = listOf(
            anyOngoingGame.players[0].playablePile.last(),
            anyOngoingGame.players[1].playablePile.last()
        )
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCards = playedCards
        )

        assertThat(newGameState.players[0].playablePile)
            .isEqualTo(anyOngoingGame.players[0].playablePile.dropLast(1))
        assertThat(newGameState.players[1].playablePile)
            .isEqualTo(anyOngoingGame.players[1].playablePile.dropLast(1))
    }

    @Test
    fun `updateGame should update game adding played cards to the winner player discard pile`() {
        val playedCards = listOf(
            anyOngoingGame.players[0].playablePile.last(),
            anyOngoingGame.players[1].playablePile.last()
        )
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCards = playedCards
        )

        assertThat(newGameState.players[0].discardPile)
            .isEqualTo(
                anyOngoingGame.players[0].discardPile + playedCards
            )
        assertThat(newGameState.players[1].discardPile)
            .isEqualTo(anyOngoingGame.players[1].discardPile)
    }

    @Test
    fun `updateGame should update game with the same suits priority`() {
        val playedCards = listOf(
            anyOngoingGame.players[0].playablePile.last(),
            anyOngoingGame.players[1].playablePile.last()
        )
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCards = playedCards
        )

        assertThat(newGameState.suitsPriority).isEqualTo(anyOngoingGame.suitsPriority)
    }

    @Test
    fun `updateGame should update game adding a new round with the cards and the winner name`() {
        val playedCards = listOf(
            anyOngoingGame.players[0].playablePile.last(),
            anyOngoingGame.players[1].playablePile.last()
        )
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCards = playedCards
        )

        assertThat(newGameState.rounds)
            .isEqualTo(
                anyOngoingGame.rounds +
                        Round(
                            playedCards,
                            anyOngoingGame.players[0].name
                        )
            )
    }

    private fun buildUpdater() = GameRoundUpdater()
}
