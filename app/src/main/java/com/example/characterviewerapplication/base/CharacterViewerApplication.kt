package com.example.characterviewerapplication.base
import android.app.Application

/**
 * Base Application class for this Project
 */
class CharacterViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Add any initialization code here
    }

    override fun onTerminate() {
        // Add any termination code here
        super.onTerminate()
    }
}

