package com.andcch.game.di

import com.andcch.game.data.di.GameDataModule
import dagger.Module

@Module(includes = [GameActivityBuilderModule::class, GameDataModule::class])
class GameFeatureModule
