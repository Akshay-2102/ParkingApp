package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface OtpRepository {

    suspend fun updateOtp(
        mobileNo: String,
        otp: String,
    )

    suspend fun updateIsSignupCompleted(
        mobileNo: String,
        isSignupCompleted: Boolean
    )

    fun getUsers(): Flow<List<User>>

}