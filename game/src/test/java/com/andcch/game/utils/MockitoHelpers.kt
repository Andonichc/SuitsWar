package com.andcch.game.utils

import org.mockito.Mockito

// In a real project with more features this class should be in a different module
// common to all modules that use mockito in its tests

fun <T> eq(obj: T): T = Mockito.eq<T>(obj)
