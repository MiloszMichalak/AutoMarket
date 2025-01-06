package com.menene.automarket.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AutoMarketApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}