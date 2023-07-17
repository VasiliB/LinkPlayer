package com.example.linkplayer

import android.app.Application
import com.example.linkplayer.di.appModule
import com.example.linkplayer.di.domainModule
import com.example.linkplayer.di.repoModule
import com.example.linkplayer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, domainModule, viewModelModule))
        }
    }
}
