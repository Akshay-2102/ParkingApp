package com.aks.parkingapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aks.parkingapp.data.local.dao.UserDao
import com.aks.parkingapp.data.local.dao.VehicleDao
import com.aks.parkingapp.data.local.entity.UserEntity
import com.aks.parkingapp.data.local.entity.VehicleEntity

@Database(entities = [UserEntity::class, VehicleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun userDao(): UserDao
}