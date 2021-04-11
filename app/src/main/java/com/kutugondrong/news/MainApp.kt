package com.kutugondrong.news

import android.app.Application
import com.kutugondrong.news.di.apiModule
import com.kutugondrong.news.di.repositoryModule
import com.kutugondrong.news.di.retrofitModule
import com.kutugondrong.news.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)
            androidFileProperties()
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(retrofitModule, apiModule, repositoryModule, viewModelModule)
}
