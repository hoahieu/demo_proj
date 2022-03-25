package com.hoahieu.demo

import android.app.Application
import com.hoahieu.demo.common.commonModule
import com.hoahieu.demo.data.di.dataModule
import com.hoahieu.demo.presentation.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(commonModule, dataModule, uiModule)
        }
    }
}