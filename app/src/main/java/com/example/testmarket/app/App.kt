package com.example.testmarket.app

import android.app.Application
import com.example.testmarket.di.appModule
import com.example.testmarket.di.networkModule
import com.example.testmarket.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
  override fun onCreate() {
    super.onCreate()


    // Start Koin
    startKoin {
      androidLogger()
      androidContext(this@App)
      modules(appModule, viewModelModule, networkModule)
    }
  }
}