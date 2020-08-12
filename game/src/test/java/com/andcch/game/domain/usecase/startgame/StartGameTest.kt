package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.fixtures.PlayerFixtures
import com.andcch.game.domain.fixtures.SuitsPriorityFixtures
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.repository.GameRepository
import com.andcch.game.utils.eq
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StartGameTest : PlayerFixtures, SuitsPriorityFixtures {

    @Mock
    private lateinit var playersGenerator: PlayersGenerator

    @Mock
    private lateinit var suitsPriorityGenerator: SuitsPriorityGenerator

    @Mock
    private lateinit var gameRepository: GameRepository

    @Test
    fun `execute should save a new game with generated players and priority`() {
        given(playersGenerator.generatePlayers()).willReturn(anyPlayers)
        given(suitsPriorityGenerator.generatePriority()).willReturn(anySuitsPriority)
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(
            eq(GameState.Ongoing(players = anyPlayers, suitsPriority = anySuitsPriority))
        )
    }

    private fun buildUseCase() = StartGame(playersGenerator, suitsPriorityGenerator, gameRepository)
}
