package com.andcch.game.domain.fixtures

import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.domain.model.Card
import com.andcch.game.domain.model.GameState
import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.Round

interface GameStateFixtures : PlayerFixtures, SuitsPriorityFixtures, RoundFixtures,
    PlayerStatsFixtures {

    val anyOngoingGame: GameState.Ongoing
        get() = GameState.Ongoing(
            players = anyPlayers,
            suitsPriority = anySuitsPriority,
            rounds = anyRounds
        )

    val anyNewOngoingGame: GameState.Ongoing
        get() = GameState.Ongoing(
            players = anyPlayers,
            suitsPriority = anySuitsPriority,
            rounds = emptyList()
        )

    val anyFinishedGameWithWinner: GameState.Finished.WithWinner
        get() = GameState.Finished.WithWinner(
            winnerName = ANY_PLAYER_NAME,
            playerStatsList = anyPlayerStatsList,
            rounds = anyRounds
        )

    val anyFinishedGameInTie: GameState.Finished.Tie
        get() = GameState.Finished.Tie(
            playerStatsList = anyPlayerStatsList,
            rounds = anyRounds
        )

    fun givenAnOngoingGame(
        withPlayers: List<Player> = anyPlayers,
        withSuitsPriority: Map<Card.Suit, Int> = anySuitsPriority,
        withRounds: List<Round> = anyRounds
    ): GameState.Ongoing = GameState.Ongoing(
        players = withPlayers,
        suitsPriority = withSuitsPriority,
        rounds = withRounds
    )

    fun givenPlayedCardsByPlayer() = mapOf<Card, Player>(
        anyOngoingGame.players[0].playablePile.last() to anyOngoingGame.players[0],
        anyOngoingGame.players[1].playablePile.last() to anyOngoingGame.players[1]
    )
}
