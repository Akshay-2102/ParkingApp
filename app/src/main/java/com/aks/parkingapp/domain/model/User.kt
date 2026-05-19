package com.aks.parkingapp.domain.model

data class User(
    val mobileNumber: String,
    val otp: String,
    val isSignupCompleted: Boolean = false
)
