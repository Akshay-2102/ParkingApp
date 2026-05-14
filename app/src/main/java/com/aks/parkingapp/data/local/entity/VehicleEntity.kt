package com.aks.parkingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VehicleEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vehicleNo: Int,
    val vehicleType: Int,
    val timestamp: Long

)