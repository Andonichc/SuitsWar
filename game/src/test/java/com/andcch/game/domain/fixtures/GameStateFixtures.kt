package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.Round

interface GameStateFixtures : PlayerFixtures, SuitsPriorityFixtures, RoundFixtures, PlayerStatsFixtures {

    val anyOngoingGame: GameState.Ongoing
        get() = GameState.Ongoing(
            players = anyPlayers,
            suitsPriority = anySuitsPriority,
            rounds = anyRounds
        )

    val anyFinishedGame: GameState.Finished
        get() = GameState.Finished.WithWinner(
            winnerName = anyPlayerName,
            playerStatsList = anyPlayerStatsList,
            rounds = anyRounds
        )

    fun givenAnOngoingGame(
        withPlayers: List<Player> = anyPlayers,
        withSuitsPriority: Map<Card.Suit, Int> = anySuitsPriority,
        withRounds: List<Round> = anyRounds
    ): GameState.Ongoing =
        GameState.Ongoing(
            players = withPlayers,
            suitsPriority = withSuitsPriority,
            rounds = withRounds
        )
}
