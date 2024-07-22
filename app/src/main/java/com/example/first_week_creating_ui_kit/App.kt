package com.example.first_week_creating_ui_kit

import android.app.Application
import com.example.first_week_creating_ui_kit.di.appModule
import com.example.first_week_creating_ui_kit.di.dataModule
import com.example.first_week_creating_ui_kit.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }
}