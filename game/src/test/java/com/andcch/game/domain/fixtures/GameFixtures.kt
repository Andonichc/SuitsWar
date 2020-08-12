package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.Player

interface GameFixtures : PlayerFixtures, SuitsPriorityFixtures {

    val anyOngoingGame: GameState.Ongoing
        get() = GameState.Ongoing(players = anyPlayers, suitsPriority = anySuitsPriority)

    fun givenAnOngoingGame(
        withPlayers: List<Player> = anyPlayers,
        withSuitsPriority: Map<Card.Suit, Int> = anySuitsPriority
    ): GameState.Ongoing =
        GameState.Ongoing(players = withPlayers, suitsPriority = withSuitsPriority)
}
