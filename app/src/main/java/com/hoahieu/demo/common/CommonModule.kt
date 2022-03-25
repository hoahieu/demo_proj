package com.hoahieu.demo.common

import com.hoahieu.demo.utils.DispatchFactory
import com.hoahieu.demo.utils.DispatchFactoryImpl
import org.koin.dsl.module

val commonModule = module {
    factory<DispatchFactory> { DispatchFactoryImpl }
}