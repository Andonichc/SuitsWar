package com.andcch.game.presentation.game

import com.andcch.coreui.arch.presenter.BasePresenterImpl
import javax.inject.Inject

class GamePresenterImpl @Inject constructor(view: GameView) : BasePresenterImpl<GameView>(view),
    GamePresenter
