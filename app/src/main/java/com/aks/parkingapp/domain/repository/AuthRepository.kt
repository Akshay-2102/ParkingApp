package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun registerUser(
        mobile: String
    )

    fun getUsers(): Flow<List<User>>

}