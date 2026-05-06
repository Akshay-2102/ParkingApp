package com.aks.parkingapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VehicleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}