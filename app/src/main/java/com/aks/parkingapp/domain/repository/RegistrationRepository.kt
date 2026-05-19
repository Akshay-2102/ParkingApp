package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

    suspend fun registerUser(
        user: User
    )

    fun getUsers(): Flow<List<User>>

    suspend fun clearUsers()

}