package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.GameFixtures
import com.andcch.game.domain.model.Game
import com.andcch.game.domain.model.Round
import com.andcch.game.domain.repository.GameRepository
import com.andcch.game.utils.argumentCaptor
import com.andcch.game.utils.safeCapture
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlayRoundTest : GameFixtures {

    @Mock
    private lateinit var gameRepository: GameRepository

    @Mock
    private lateinit var cardComparator: CardComparator

    @Test
    fun `execute should save game without the last card of players playable pile`() {
        val player1PlayableCard = anyNewGame.players[0].playablePile.last()
        val player2PlayableCard = anyNewGame.players[1].playablePile.last()
        given(gameRepository.getGame()).willReturn(anyNewGame)
        given(
            cardComparator.compare(
                listOf(player1PlayableCard, player2PlayableCard),
                anySuitsPriority
            )
        ).willReturn(player1PlayableCard)
        val savedGameCaptor = argumentCaptor<Game>()
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(savedGameCaptor.safeCapture())
        val savedGame = savedGameCaptor.value
        assertThat(savedGame.players[0].playablePile)
            .isEqualTo(anyNewGame.players[0].playablePile.dropLast(1))
        assertThat(savedGame.players[1].playablePile)
            .isEqualTo(anyNewGame.players[1].playablePile.dropLast(1))
    }

    @Test
    fun `execute should save game adding played cards to the winner player discard pile`() {
        val player1PlayableCard = anyNewGame.players[0].playablePile.last()
        val player2PlayableCard = anyNewGame.players[1].playablePile.last()
        given(gameRepository.getGame()).willReturn(anyNewGame)
        given(
            cardComparator.compare(
                listOf(player1PlayableCard, player2PlayableCard),
                anySuitsPriority
            )
        ).willReturn(player1PlayableCard)
        val savedGameCaptor = argumentCaptor<Game>()
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(savedGameCaptor.safeCapture())
        val savedGame = savedGameCaptor.value
        assertThat(savedGame.players[0].discardPile)
            .isEqualTo(
                anyNewGame.players[0].discardPile + player1PlayableCard + player2PlayableCard
            )
        assertThat(savedGame.players[1].discardPile).isEqualTo(anyNewGame.players[1].discardPile)
    }

    @Test
    fun `execute should save game with the same suits priority`() {
        val player1PlayableCard = anyNewGame.players[0].playablePile.last()
        val player2PlayableCard = anyNewGame.players[1].playablePile.last()
        given(gameRepository.getGame()).willReturn(anyNewGame)
        given(
            cardComparator.compare(
                listOf(player1PlayableCard, player2PlayableCard),
                anySuitsPriority
            )
        ).willReturn(player1PlayableCard)
        val savedGameCaptor = argumentCaptor<Game>()
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(savedGameCaptor.safeCapture())
        val savedGame = savedGameCaptor.value
        assertThat(savedGame.suitsPriority).isEqualTo(anyNewGame.suitsPriority)
    }

    @Test
    fun `execute should save game adding a new round with the cards and the winner name`() {
        val player1PlayableCard = anyNewGame.players[0].playablePile.last()
        val player2PlayableCard = anyNewGame.players[1].playablePile.last()
        given(gameRepository.getGame()).willReturn(anyNewGame)
        given(
            cardComparator.compare(
                listOf(player1PlayableCard, player2PlayableCard),
                anySuitsPriority
            )
        ).willReturn(player1PlayableCard)
        val savedGameCaptor = argumentCaptor<Game>()
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(savedGameCaptor.safeCapture())
        val savedGame = savedGameCaptor.value
        assertThat(savedGame.rounds)
            .isEqualTo(
                anyNewGame.rounds +
                        Round(
                            listOf(player1PlayableCard, player2PlayableCard),
                            anyNewGame.players[0].name
                        )
            )
    }

    @Test(expected = IllegalStateException::class)
    fun `execute should throw exception when game repository returns a null game`() {
        given(gameRepository.getGame()).willReturn(null)
        val useCase = buildUseCase()

        useCase.execute()
    }

    @Test(expected = IllegalStateException::class)
    fun `execute should throw exception when a player doesn't have cards to play`() {
        given(gameRepository.getGame()).willReturn(
            givenAGame(
                withPlayers = listOf(
                    givenAPlayer(withPlayablePile = emptyList())
                )
            )
        )
        val useCase = buildUseCase()

        useCase.execute()
    }

    @Test(expected = IllegalStateException::class)
    fun `execute should throw exception when winner card is not from any player`() {
        val player1PlayableCard = anyNewGame.players[0].playablePile.last()
        val player2PlayableCard = anyNewGame.players[1].playablePile.last()
        given(gameRepository.getGame()).willReturn(anyNewGame)
        given(
            cardComparator.compare(
                listOf(player1PlayableCard, player2PlayableCard),
                anySuitsPriority
            )
        ).willReturn(anySetOfCards[2])
        val useCase = buildUseCase()

        useCase.execute()
    }

    private fun buildUseCase() = PlayRound(gameRepository, cardComparator)
}
