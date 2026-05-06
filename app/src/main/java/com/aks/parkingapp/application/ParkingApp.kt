package com.aks.parkingapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ParkingApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}