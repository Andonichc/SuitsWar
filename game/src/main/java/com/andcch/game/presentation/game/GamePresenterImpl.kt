package com.andcch.game.presentation.game

import com.andcch.coreui.arch.presenter.BasePresenterImpl
import com.andcch.coreui.coroutines.Dispatchers
import com.andcch.game.domain.usecase.getgameupdates.GetGameUpdates
import com.andcch.game.domain.usecase.getsavedgame.GetSavedGame
import com.andcch.game.domain.usecase.playround.PlayRound
import com.andcch.game.domain.usecase.startgame.StartGame
import com.andcch.game.presentation.game.mapper.GameViewModelStateMapper
import com.andcch.game.presentation.game.model.GameViewModelState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GamePresenterImpl @Inject constructor(
    view: GameView,
    private val getGameUpdates: GetGameUpdates,
    private val playRound: PlayRound,
    private val startGame: StartGame,
    private val getSavedGame: GetSavedGame,
    private val gameViewModelStateMapper: GameViewModelStateMapper,
    private val dispatchers: Dispatchers
) : BasePresenterImpl<GameView>(view, dispatchers), GamePresenter {

    private var blockCalls: Boolean = false
    private var cleanStart: Boolean = true

    override fun onParamsProvided(cleanStart: Boolean) {
        this.cleanStart = cleanStart
    }

    override fun onViewReady() {
        subscribeToGameUpdates()
        if (cleanStart) {
            startNewGame()
        } else {
            getLastGameState()
        }
    }

    override fun onPlayRoundTap() {
        runJob {
            if (blockCalls) return@runJob
            blockCalls = true
            withContext(dispatchers.default) {
                playRound.execute()
            }
        }
    }

    override fun onResetGameTap() {
        startNewGame()
    }

    private fun subscribeToGameUpdates() {
        runJob {
            getGameUpdates.execute()
                .map { gameState -> gameViewModelStateMapper.transform(gameState) }
                .flowOn(dispatchers.default)
                .collect { gameStateViewModel ->
                    renderGameState(gameStateViewModel)
                    blockCalls = false
                }
        }
    }

    private fun renderGameState(gameState: GameViewModelState) {
        view.showRounds(gameState.rounds)
        view.scrollToBottom()
        view.showPlayers(gameState.players)
        view.showGameStatusText(gameState.gameStatusText)
        if (gameState.isPlayable) {
            view.enablePlayButton()
        } else {
            view.disablePlayButton()
        }
    }

    private fun startNewGame() {
        runJob {
            if (blockCalls) return@runJob
            blockCalls = true
            withContext(dispatchers.default) {
                startGame.execute()
            }
        }
    }

    private fun getLastGameState() {
        runJob {
            val gameStateViewModel: GameViewModelState? = withContext(dispatchers.default) {
                getSavedGame.execute()?.let(gameViewModelStateMapper::transform)
            }
            gameStateViewModel?.let(::renderGameState)
        }
    }
}
