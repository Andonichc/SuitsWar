package com.andcch.game.presentation.game.mapper

import android.content.Context
import androidx.annotation.StringRes
import com.andcch.coreui.di.qualifier.ForApplication
import com.andcch.game.domain.model.Card
import com.andcch.game.presentation.game.model.CardValues
import com.andcch.game.presentation.game.model.SuitValues
import javax.inject.Inject

// TODO Tests for this class are missing due to timing
class CardTextProvider @Inject constructor(@ForApplication private val context: Context) {

    fun transform(card: Card): String =
        context.getString(transform(card.suit), transform(card.value))

    @StringRes
    private fun transform(suit: Card.Suit): Int = when (suit) {
        Card.Suit.CLUBS -> SuitValues.CLUBS
        Card.Suit.DIAMONDS -> SuitValues.DIAMONDS
        Card.Suit.HEARTS -> SuitValues.HEARTS
        Card.Suit.SPADES -> SuitValues.SPADES
    }.textResource

    private fun transform(value: Card.Value): String = when (value) {
        Card.Value.N2 -> CardValues.N2
        Card.Value.N3 -> CardValues.N3
        Card.Value.N4 -> CardValues.N4
        Card.Value.N5 -> CardValues.N5
        Card.Value.N6 -> CardValues.N6
        Card.Value.N7 -> CardValues.N7
        Card.Value.N8 -> CardValues.N8
        Card.Value.N9 -> CardValues.N9
        Card.Value.N10 -> CardValues.N10
        Card.Value.J -> CardValues.J
        Card.Value.Q -> CardValues.Q
        Card.Value.K -> CardValues.K
        Card.Value.A -> CardValues.A
    }.text
}
