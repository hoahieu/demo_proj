package com.hoahieu.demo.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchFactory {
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}