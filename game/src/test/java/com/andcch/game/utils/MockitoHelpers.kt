package com.andcch.game.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.stubbing.Answer

// In a real project with more features this class should be in a different module
// common to all modules that use mockito in its tests

inline fun <reified T : Any> mock(answer: Answer<Any> = Mockito.RETURNS_DEFAULTS): T =
    Mockito.mock(T::class.java, answer)

fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

fun <T> ArgumentCaptor<T>.safeCapture(): T = capture()
