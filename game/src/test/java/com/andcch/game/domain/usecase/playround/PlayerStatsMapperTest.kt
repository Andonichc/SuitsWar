package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.PlayerFixtures
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anyPlayerName
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.anySecondPlayerName
import com.andcch.game.domain.fixtures.PlayerStatsFixtures
import com.andcch.game.domain.model.Player
import com.andcch.game.domain.model.PlayerStats
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PlayerStatsMapperTest(
    private val players: List<Player>,
    private val expectedPlayerStatsList: List<PlayerStats>
) {

    private companion object : PlayerFixtures, PlayerStatsFixtures {

        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data() = listOf(
            // No players
            arrayOf(emptyList<Player>(), emptyList<PlayerStats>()),

            // One player
            arrayOf(
                listOf(
                    givenAPlayer(withName = anyPlayerName, withDiscardPile = anySetOfCards.take(11))
                ),
                listOf(
                    givenAPlayerStats(withName = anyPlayerName, withPoints = 11)
                )
            ),

            // Several players
            arrayOf(
                listOf(
                    givenAPlayer(withName = anyPlayerName),
                    givenAPlayer(
                        withName = anySecondPlayerName, withDiscardPile = anySetOfCards.take(4)
                    ),
                    givenAPlayer(
                        withName = anyPlayerName.plus("3"), withDiscardPile = anySetOfCards.take(1)
                    )
                ),
                listOf(
                    givenAPlayerStats(withName = anyPlayerName),
                    givenAPlayerStats(withName = anySecondPlayerName, withPoints = 4),
                    givenAPlayerStats(withName = anyPlayerName.plus("3"), withPoints = 1)
                )
            )
        )
    }

    @Test
    fun `transform should transform players into player stats`() {
        val mapper = buildMapper()

        val playerStatsList = mapper.transform(players)

        assertThat(playerStatsList).isEqualTo(expectedPlayerStatsList)
    }

    private fun buildMapper() = PlayerStatsMapper()
}
