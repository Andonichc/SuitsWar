package com.andcch.game.domain.usecase.playround

import com.andcch.game.domain.fixtures.PlayerFixtures
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_PLAYER_NAME
import com.andcch.game.domain.fixtures.PlayerFixtures.Companion.ANY_SECOND_PLAYER_NAME
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
                    givenAPlayer(withName = ANY_PLAYER_NAME, withDiscardPile = anySetOfCards.take(11))
                ),
                listOf(
                    givenAPlayerStats(withName = ANY_PLAYER_NAME, withPoints = 11)
                )
            ),

            // Several players
            arrayOf(
                listOf(
                    givenAPlayer(withName = ANY_PLAYER_NAME),
                    givenAPlayer(
                        withName = ANY_SECOND_PLAYER_NAME, withDiscardPile = anySetOfCards.take(4)
                    ),
                    givenAPlayer(
                        withName = ANY_PLAYER_NAME.plus("3"), withDiscardPile = anySetOfCards.take(1)
                    )
                ),
                listOf(
                    givenAPlayerStats(withName = ANY_PLAYER_NAME),
                    givenAPlayerStats(withName = ANY_SECOND_PLAYER_NAME, withPoints = 4),
                    givenAPlayerStats(withName = ANY_PLAYER_NAME.plus("3"), withPoints = 1)
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
