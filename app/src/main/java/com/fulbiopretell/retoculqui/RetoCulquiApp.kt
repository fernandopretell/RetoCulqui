package com.fulbiopretell.retoculqui

import android.app.Application
import com.limapps.mitrueque2.preferences.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RetoCulquiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.init(this) // inicio sharedpreferences
    }
}