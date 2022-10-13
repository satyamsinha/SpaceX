package com.example.spacex

import android.app.Application
import com.example.spacex.common.injection.commonModule
import com.example.spacex.common.injection.homeModule
import org.koin.core.context.startKoin

class SpaceXApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() = startKoin {
        modules(listOf(commonModule, homeModule))
    }
}