package com.andcch.game.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

// In a real project with more features this class should be in a different module
// common to all modules that use mockito in its tests

fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

fun <T> ArgumentCaptor<T>.safeCapture(): T = capture()
