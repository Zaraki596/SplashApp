package com.example.splashapp

import android.app.Application
import com.example.splashapp.di.AppResolver

class SplashApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppResolver.initializeApp(this)
    }
}