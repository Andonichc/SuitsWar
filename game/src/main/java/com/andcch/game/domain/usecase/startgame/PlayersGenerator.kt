package com.andcch.game.domain.usecase.startgame

import com.andcch.game.domain.model.Player
import javax.inject.Inject
import kotlin.math.min

class PlayersGenerator @Inject constructor(
    private val pokerCardsSetGenerator: PokerCardsSetGenerator
) {

    private companion object {
        // if in the future we want to add more players we can pass this as a parameter
        // in generatePlayers() (and in the usecase)
        private const val NUMBER_OF_PLAYERS = 2
    }

    fun generatePlayers(): List<Player> {
        val shuffledCards = pokerCardsSetGenerator.generateCardsSet().shuffled()
        val initialCardsPerPlayer = shuffledCards.size / NUMBER_OF_PLAYERS

        return (0 until NUMBER_OF_PLAYERS).map { playerNumber ->
            Player(
                name = "Player $playerNumber",
                playablePile = shuffledCards.subList(
                    initialCardsPerPlayer * playerNumber,
                    min(initialCardsPerPlayer * playerNumber.inc(), shuffledCards.size)
                ).toMutableList()
            )
        }
    }
}
