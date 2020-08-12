package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.GameStateFixtures
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.andcch.game.domain.fixtures.PlayerStatsFixtures
import com.andcch.game.domain.model.GameState
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FinishedGameUpdaterTest : GameStateFixtures, PlayerStatsFixtures {

    @Mock
    private lateinit var playerStatsMapper: PlayerStatsMapper

    @Test(expected = IllegalArgumentException::class)
    fun `updateGame should throw exception when game has no players`() {
        given(playerStatsMapper.transform(anyOngoingGame.players)).willReturn(emptyList())
        val updater = buildUpdater()

        updater.updateGame(anyOngoingGame)
    }

    @Test
    fun `updateGame should finish with winner when there's one player`() {
        given(playerStatsMapper.transform(anyOngoingGame.players)).willReturn(
            anyPlayerStatsList.take(1)
        )
        val updater = buildUpdater()

        val finishedGameState = updater.updateGame(anyOngoingGame)

        assertThat(finishedGameState).isEqualTo(
            GameState.Finished.WithWinner(
                winnerName = anyPlayerName,
                playerStatsList = anyPlayerStatsList.take(1),
                rounds = anyOngoingGame.rounds
            )
        )
    }

    @Test
    fun `updateGame should finish with winner when one player has the highest score`() {
        given(playerStatsMapper.transform(anyOngoingGame.players)).willReturn(anyPlayerStatsList)
        val updater = buildUpdater()

        val finishedGameState = updater.updateGame(anyOngoingGame)

        assertThat(finishedGameState).isEqualTo(
            GameState.Finished.WithWinner(
                winnerName = anyPlayerName,
                playerStatsList = anyPlayerStatsList,
                rounds = anyOngoingGame.rounds
            )
        )
    }

    @Test
    fun `updateGame should finish in tie when two players have the highest score`() {
        val playerStatsList = listOf(
            givenAPlayerStats(withName = anyPlayerName, withPoints = 26),
            givenAPlayerStats(withName = anySecondPlayerName, withPoints = 26)
        )
        given(playerStatsMapper.transform(anyOngoingGame.players)).willReturn(playerStatsList)
        val updater = buildUpdater()

        val finishedGameState = updater.updateGame(anyOngoingGame)

        assertThat(finishedGameState).isEqualTo(
            GameState.Finished.Tie(
                playerStatsList = playerStatsList,
                rounds = anyOngoingGame.rounds
            )
        )
    }

    private fun buildUpdater() = FinishedGameUpdater(playerStatsMapper)
}
