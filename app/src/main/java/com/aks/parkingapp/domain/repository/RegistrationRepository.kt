package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.data.remote.registerDTO.RegisterRequestDTO
import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.model.register.RegisterRequest
import com.aks.parkingapp.domain.model.register.RegisterResult
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

    suspend fun registerUser(
        request: RegisterRequest
    ): Result<RegisterResult>

}