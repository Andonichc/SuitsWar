package com.andcch.game.presentation.game

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.andcch.coreui.arch.view.BaseActivity
import com.andcch.game.R
import com.andcch.game.presentation.game.model.PlayerViewModel
import com.andcch.game.presentation.game.model.RoundViewModel
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : BaseActivity<GamePresenter>(), GameView {

    override val layout: Int = R.layout.activity_game

    private val playersAdapter = PlayersAdapter()
    private val roundsAdapter = RoundsAdapter()

    //region INITIALIZATION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeRoundsRecyclerView()
        initializePlayersRecyclerView()
        initializeOnClickListeners()
        presenter.onParamsProvided(savedInstanceState == null)
        presenter.onViewReady()
    }

    private fun initializeRoundsRecyclerView() {
        rvRounds.adapter = roundsAdapter
        rvRounds.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initializePlayersRecyclerView() {
        rvPlayers.adapter = playersAdapter
        rvPlayers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initializeOnClickListeners() {
        btnPlayRound.setOnClickListener {
            presenter.onPlayRoundTap()
        }
        fabReset.setOnClickListener {
            presenter.onResetGameTap()
        }
    }
    //endregion

    //region VIEW
    override fun showRounds(rounds: List<RoundViewModel>) {
        roundsAdapter.submitList(rounds)
    }

    override fun showPlayers(players: List<PlayerViewModel>) {
        playersAdapter.submitList(players)
    }

    override fun showGameStatusText(text: String) {
        tvGameStatus.text = text
    }

    override fun showErrorMessage(@StringRes errorMessage: Int) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun disablePlayButton() {
        btnPlayRound.isEnabled = false
    }

    override fun enablePlayButton() {
        btnPlayRound.isEnabled = true
    }

    override fun scrollToBottom() {
        rvRounds.smoothScrollToPosition(roundsAdapter.itemCount)
    }
    //endregion
}
