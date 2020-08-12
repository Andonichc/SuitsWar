package com.andcch.game.presentation.game.model

import androidx.annotation.StringRes
import com.andcch.game.R

enum class SuitValues(@StringRes val textResource: Int) {
    CLUBS(R.string.clubs_card),
    DIAMONDS(R.string.diamonds_card),
    HEARTS(R.string.hearts_card),
    SPADES(R.string.spades_card)
}
