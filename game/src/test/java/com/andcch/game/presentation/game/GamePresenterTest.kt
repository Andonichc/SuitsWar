package com.andcch.game.presentation.game

import com.andcch.game.domain.fixtures.GameStateFixtures
import com.andcch.game.domain.usecase.getgameupdates.GetGameUpdates
import com.andcch.game.domain.usecase.getsavedgame.GetSavedGame
import com.andcch.game.domain.usecase.playround.PlayRound
import com.andcch.game.domain.usecase.startgame.StartGame
import com.andcch.game.presentation.fixtures.GameStateViewModelFixtures
import com.andcch.game.presentation.game.mapper.GameViewModelStateMapper
import com.andcch.game.utils.TestDispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.clearInvocations
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GamePresenterTest : GameStateFixtures, GameStateViewModelFixtures {

    @Mock
    private lateinit var view: GameView

    @Mock
    private lateinit var getGameUpdates: GetGameUpdates

    @Mock
    private lateinit var playRound: PlayRound

    @Mock
    private lateinit var startGame: StartGame

    @Mock
    private lateinit var getSavedGame: GetSavedGame

    @Mock
    private lateinit var mapper: GameViewModelStateMapper

    //region onViewReady
    @Test
    fun `onViewReady should show rounds when game updates`() {
        given(getGameUpdates.execute()).willReturn(flowOf(anyOngoingGame))
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()

        presenter.onViewReady()

        verify(view).showRounds(anyPlayableGameStateViewModel.rounds)
    }

    @Test
    fun `onViewReady should scroll to bottom when game updates`() {
        given(getGameUpdates.execute()).willReturn(flowOf(anyOngoingGame))
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()

        presenter.onViewReady()

        verify(view).scrollToBottom()
    }

    @Test
    fun `onViewReady should show players when game updates`() {
        given(getGameUpdates.execute()).willReturn(flowOf(anyOngoingGame))
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()

        presenter.onViewReady()

        verify(view).showPlayers(anyPlayableGameStateViewModel.players)
    }

    @Test
    fun `onViewReady should show game status text when game updates`() {
        given(getGameUpdates.execute()).willReturn(flowOf(anyOngoingGame))
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()

        presenter.onViewReady()

        verify(view).showGameStatusText(anyPlayableGameStateViewModel.gameStatusText)
    }

    @Test
    fun `onViewReady should show enable play button when game updates and is playable`() {
        given(getGameUpdates.execute()).willReturn(flowOf(anyOngoingGame))
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()

        presenter.onViewReady()

        verify(view).enablePlayButton()
    }

    @Test
    fun `onViewReady should show disable play button when game updates and is not playable`() {
        given(getGameUpdates.execute()).willReturn(flowOf(anyOngoingGame))
        given(mapper.transform(anyOngoingGame)).willReturn(anyNonPlayableGameStateViewModel)
        val presenter = buildPresenter()

        presenter.onViewReady()

        verify(view).disablePlayButton()
    }
    //endregion

    //region onViewReady - clean start
    @Test
    fun `onViewReady should start a new game when starting from a clean start`() {
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = true)

        presenter.onViewReady()

        verify(startGame).execute()
    }
    //endregion

    //region onViewReady - not clean start
    @Test
    fun `onViewReady should render saved game rounds when starting from a non clean start`() {
        given(getSavedGame.execute()).willReturn(anyOngoingGame)
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = false)

        presenter.onViewReady()

        verify(view).showRounds(anyPlayableGameStateViewModel.rounds)
    }

    @Test
    fun `onViewReady should scroll to bottom when starting from a non clean start`() {
        given(getSavedGame.execute()).willReturn(anyOngoingGame)
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = false)

        presenter.onViewReady()

        verify(view).scrollToBottom()
    }

    @Test
    fun `onViewReady should render saved game players when starting from a non clean start`() {
        given(getSavedGame.execute()).willReturn(anyOngoingGame)
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = false)

        presenter.onViewReady()

        verify(view).showPlayers(anyPlayableGameStateViewModel.players)
    }

    @Test
    fun `onViewReady should render saved game status text when starting from a non clean start`() {
        given(getSavedGame.execute()).willReturn(anyOngoingGame)
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = false)

        presenter.onViewReady()

        verify(view).showGameStatusText(anyPlayableGameStateViewModel.gameStatusText)
    }

    @Test
    fun `onViewReady should enable play button when starting from a non clean start and saved game is playable`() {
        given(getSavedGame.execute()).willReturn(anyOngoingGame)
        given(mapper.transform(anyOngoingGame)).willReturn(anyPlayableGameStateViewModel)
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = false)

        presenter.onViewReady()

        verify(view).enablePlayButton()
    }

    @Test
    fun `onViewReady should disable play button when starting from a non clean start and saved game is not playable`() {
        given(getSavedGame.execute()).willReturn(anyOngoingGame)
        given(mapper.transform(anyOngoingGame)).willReturn(anyNonPlayableGameStateViewModel)
        val presenter = buildPresenter()
        presenter.onParamsProvided(cleanStart = false)

        presenter.onViewReady()

        verify(view).disablePlayButton()
    }
    //endregion

    //region onPlayRoundTap
    @Test
    fun `onPlayRoundTap should execute play round`() {
        val presenter = buildPresenter()

        presenter.onPlayRoundTap()

        verify(playRound).execute()
    }

    @Test
    fun `onPlayRoundTap should not execute play round if it already has been executed`() {
        val presenter = buildPresenter()
        presenter.onPlayRoundTap()
        clearInvocations(playRound)

        presenter.onPlayRoundTap()

        verifyNoInteractions(playRound)
    }
    //endregion

    //region onResetGameTap
    @Test
    fun `onResetGameTap should execute play round`() {
        val presenter = buildPresenter()

        presenter.onResetGameTap()

        verify(startGame).execute()
    }

    @Test
    fun `onResetGameTap should not execute play round if it already has been executed`() {
        val presenter = buildPresenter()
        presenter.onResetGameTap()
        clearInvocations(startGame)

        presenter.onResetGameTap()

        verifyNoInteractions(startGame)
    }
    //endregion

    private fun buildPresenter() = GamePresenterImpl(
        view, getGameUpdates, playRound, startGame, getSavedGame, mapper, TestDispatchers()
    )
}
