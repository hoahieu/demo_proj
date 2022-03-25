package com.hoahieu.demo.utils

import kotlinx.coroutines.Dispatchers

object DispatchFactoryImpl : DispatchFactory {
    override fun io() = Dispatchers.IO
    override fun default() = Dispatchers.Default
    override fun main() = Dispatchers.Main
    override fun unconfined() = Dispatchers.Unconfined
}