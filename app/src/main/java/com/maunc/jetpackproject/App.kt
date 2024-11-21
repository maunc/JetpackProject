package com.maunc.jetpackproject

import android.app.Application
import com.maunc.jetpackproject.utils.DataStorePreferences

class App : Application() {

    companion object {
        lateinit var app: Application

        fun getApplication() :Application{
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        DataStorePreferences.init(this)
    }

}