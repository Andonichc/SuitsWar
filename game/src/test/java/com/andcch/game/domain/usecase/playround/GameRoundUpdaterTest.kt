package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.GameStateFixtures
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_SECOND_PLAYER_NAME
import com.andcch.game.domain.model.PlayerCard
import com.andcch.game.domain.model.Round
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GameRoundUpdaterTest : GameStateFixtures {

    @Test
    fun `updateGame should update game without the last card of players playable pile`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCardsByPlayer = playedCardsByPlayer
        )

        assertThat(newGameState.players[0].playablePile)
            .isEqualTo(anyOngoingGame.players[0].playablePile.dropLast(1))
        assertThat(newGameState.players[1].playablePile)
            .isEqualTo(anyOngoingGame.players[1].playablePile.dropLast(1))
    }

    @Test
    fun `updateGame should update game adding played cards to the winner player discard pile`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCardsByPlayer = playedCardsByPlayer
        )

        assertThat(newGameState.players[0].discardPile)
            .isEqualTo(
                anyOngoingGame.players[0].discardPile + playedCardsByPlayer.keys
            )
        assertThat(newGameState.players[1].discardPile)
            .isEqualTo(anyOngoingGame.players[1].discardPile)
    }

    @Test
    fun `updateGame should update game with the same suits priority`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCardsByPlayer = playedCardsByPlayer
        )

        assertThat(newGameState.suitsPriority).isEqualTo(anyOngoingGame.suitsPriority)
    }

    @Test
    fun `updateGame should update game adding a new round with the cards and the winner name`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        val updater = buildUpdater()

        val newGameState = updater.updateGame(
            gameState = anyOngoingGame,
            winnerPlayer = anyOngoingGame.players[0],
            playedCardsByPlayer = playedCardsByPlayer
        )

        assertThat(newGameState.rounds)
            .isEqualTo(
                anyOngoingGame.rounds +
                        Round(
                            listOf(
                                PlayerCard(
                                    ANY_PLAYER_NAME, anyOngoingGame.players[0].playablePile.last()
                                ),
                                PlayerCard(
                                    ANY_SECOND_PLAYER_NAME,
                                    anyOngoingGame.players[1].playablePile.last()
                                )
                            ),
                            anyOngoingGame.players[0].name
                        )
            )
    }

    private fun buildUpdater() = GameRoundUpdater()
}
