package com.andcch.game.presentation.game

import com.andcch.coreui.arch.view.BasePresenter
import com.andcch.coreui.arch.view.BaseView
import com.andcch.game.presentation.game.model.PlayerViewModel
import com.andcch.game.presentation.game.model.RoundViewModel

interface GameView : BaseView {

    fun showRounds(rounds: List<RoundViewModel>)

    fun showPlayers(players: List<PlayerViewModel>)

    fun showGameStatusText(text: String)

    fun disablePlayButton()

    fun enablePlayButton()

    fun scrollToBottom()
}

interface GamePresenter : BasePresenter {

    fun onParamsProvided(cleanStart: Boolean)

    fun onViewReady()

    fun onPlayRoundTap()

    fun onResetGameTap()
}
