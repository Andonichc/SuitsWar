package com.andcch.game.presentation.game.mapper

import android.test.mock.MockContext
import com.andcch.game.R
import com.andcch.game.domain.fixtures.GameStateFixtures
import com.andcch.game.presentation.fixtures.PlayerViewModelFixtures
import com.andcch.game.presentation.fixtures.RoundViewModelFixtures
import com.andcch.game.presentation.game.model.GameViewModelState
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameViewModelStateMapperTest : GameStateFixtures, PlayerViewModelFixtures,
    RoundViewModelFixtures {

    private companion object {
        private const val ANY_STRING_RES = "AnyStringRes"
    }

    @Mock
    private lateinit var context: MockContext

    @Mock
    private lateinit var playerViewModelMapper: PlayerViewModelMapper

    @Mock
    private lateinit var roundViewModelMapper: RoundViewModelMapper

    @Test
    fun `transform should transform a new ongoing game`() {
        mockPlayerMapper()
        mockRoundMapper()
        given(context.getString(R.string.game_state_start)).willReturn(ANY_STRING_RES)
        val mapper = buildMapper()

        val gameStateViewModel = mapper.transform(anyNewOngoingGame)

        assertThat(gameStateViewModel).isEqualTo(
            GameViewModelState(
                players = amyViewModelPlayers,
                rounds = emptyList(),
                gameStateText = ANY_STRING_RES,
                isPlayable = true
            )
        )
    }

    @Test
    fun `transform should transform an ongoing game`() {
        mockPlayerMapper()
        mockRoundMapper()
        given(context.getString(R.string.game_state_round, anyRounds.last().winnerName))
            .willReturn(ANY_STRING_RES)
        val mapper = buildMapper()

        val gameStateViewModel = mapper.transform(anyOngoingGame)

        assertThat(gameStateViewModel).isEqualTo(
            GameViewModelState(
                players = amyViewModelPlayers,
                rounds = anyViewModelRounds,
                gameStateText = ANY_STRING_RES,
                isPlayable = true
            )
        )
    }

    @Test
    fun `transform should transform a finished game with winner`() {
        mockPlayerStatsMapper()
        mockRoundMapper()
        given(context.getString(R.string.game_state_winner, anyFinishedGameWithWinner.winnerName))
            .willReturn(ANY_STRING_RES)
        val mapper = buildMapper()

        val gameStateViewModel = mapper.transform(anyFinishedGameWithWinner)

        assertThat(gameStateViewModel).isEqualTo(
            GameViewModelState(
                players = amyViewModelPlayers,
                rounds = anyViewModelRounds,
                gameStateText = ANY_STRING_RES,
                isPlayable = false
            )
        )
    }

    @Test
    fun `transform should transform a finished game in tie`() {
        mockPlayerStatsMapper()
        mockRoundMapper()
        given(context.getString(R.string.game_state_tie)).willReturn(ANY_STRING_RES)
        val mapper = buildMapper()

        val gameStateViewModel = mapper.transform(anyFinishedGameInTie)

        assertThat(gameStateViewModel).isEqualTo(
            GameViewModelState(
                players = amyViewModelPlayers,
                rounds = anyViewModelRounds,
                gameStateText = ANY_STRING_RES,
                isPlayable = false
            )
        )
    }

    private fun mockPlayerMapper() {
        given(playerViewModelMapper.transform(anyPlayers[0])).willReturn(amyViewModelPlayers[0])
        given(playerViewModelMapper.transform(anyPlayers[1])).willReturn(amyViewModelPlayers[1])
    }

    private fun mockPlayerStatsMapper() {
        given(playerViewModelMapper.transform(anyPlayerStatsList[0]))
            .willReturn(amyViewModelPlayers[0])
        given(playerViewModelMapper.transform(anyPlayerStatsList[1]))
            .willReturn(amyViewModelPlayers[1])
    }

    private fun mockRoundMapper() {
        given(roundViewModelMapper.transform(anyRounds[0])).willReturn(anyViewModelRounds[0])
        given(roundViewModelMapper.transform(anyRounds[1])).willReturn(anyViewModelRounds[1])
    }

    private fun buildMapper() =
        GameViewModelStateMapper(context, playerViewModelMapper, roundViewModelMapper)
}
