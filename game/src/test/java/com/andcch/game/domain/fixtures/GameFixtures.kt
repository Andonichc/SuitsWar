package com.andcch.game.domain.fixtures

import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.Game
import com.andcch.game.domain.model.Player

interface GameFixtures : PlayerFixtures, SuitsPriorityFixtures {

    val anyNewGame: Game
        get() = Game(players = anyPlayers, suitsPriority = anySuitsPriority)

    fun givenAGame(
        withPlayers: List<Player> = anyPlayers,
        withSuitsPriority: Map<Card.Suit, Int> = anySuitsPriority
    ): Game = Game(players = withPlayers, suitsPriority = withSuitsPriority)
}
