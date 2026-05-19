package com.aks.parkingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val mobileNumber: String,
    val otp: String,
    val isSignupCompleted: Boolean = false
)