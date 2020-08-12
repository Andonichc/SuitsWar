package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.GameStateFixtures
import com.andcch.game.domain.repository.GameRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlayRoundTest : GameStateFixtures {

    @Mock
    private lateinit var gameRepository: GameRepository

    @Mock
    private lateinit var cardComparator: CardComparator

    @Mock
    private lateinit var gameRoundUpdater: GameRoundUpdater

    @Mock
    private lateinit var finishedGameUpdater: FinishedGameUpdater

    @Test(expected = IllegalStateException::class)
    fun `execute should throw exception when game repository returns a null game`() {
        given(gameRepository.getGame()).willReturn(null)
        val useCase = buildUseCase()

        useCase.execute()
    }

    @Test(expected = IllegalStateException::class)
    fun `execute should throw exception when no player has cards to play`() {
        given(gameRepository.getGame()).willReturn(
            givenAnOngoingGame(
                withPlayers = listOf(
                    givenAPlayer(withPlayablePile = emptyList()),
                    givenAPlayer(withPlayablePile = emptyList())
                )
            )
        )
        val useCase = buildUseCase()

        useCase.execute()
    }

    @Test(expected = IllegalStateException::class)
    fun `execute should throw exception when winner card is not from any player`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        given(gameRepository.getGame()).willReturn(anyOngoingGame)
        given(cardComparator.compare(playedCardsByPlayer.keys.toList(), anySuitsPriority))
            .willReturn(anySetOfCards[2])
        val useCase = buildUseCase()

        useCase.execute()
    }

    @Test
    fun `execute should save ongoing game when game can continue`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        val anyUpdatedGameState = givenAnOngoingGame()
        given(gameRepository.getGame()).willReturn(anyOngoingGame)
        given(cardComparator.compare(playedCardsByPlayer.keys.toList(), anySuitsPriority))
            .willReturn(playedCardsByPlayer.keys.first())
        given(
            gameRoundUpdater.updateGame(
                anyOngoingGame,
                anyOngoingGame.players[0],
                playedCardsByPlayer
            )
        ).willReturn(anyUpdatedGameState)
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(anyUpdatedGameState)
    }

    @Test
    fun `execute should save finished game when game cannot continue`() {
        val playedCardsByPlayer = givenPlayedCardsByPlayer()
        val anyUpdatedGameState = givenAnOngoingGame(
            withPlayers = listOf(
                givenAPlayer(withPlayablePile = emptyList()),
                givenAPlayer(withPlayablePile = emptyList())
            )
        )
        given(gameRepository.getGame()).willReturn(anyOngoingGame)
        given(cardComparator.compare(playedCardsByPlayer.keys.toList(), anySuitsPriority))
            .willReturn(playedCardsByPlayer.keys.first())
        given(
            gameRoundUpdater.updateGame(
                anyOngoingGame,
                anyOngoingGame.players[0],
                playedCardsByPlayer
            )
        ).willReturn(anyUpdatedGameState)
        given(finishedGameUpdater.updateGame(anyUpdatedGameState))
            .willReturn(anyFinishedGameWithWinner)
        val useCase = buildUseCase()

        useCase.execute()

        verify(gameRepository).saveGame(anyFinishedGameWithWinner)
    }

    private fun buildUseCase() =
        PlayRound(gameRepository, cardComparator, gameRoundUpdater, finishedGameUpdater)
}
