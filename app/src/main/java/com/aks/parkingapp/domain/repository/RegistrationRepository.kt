package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.domain.model.register.RegisterRequestModel
import com.aks.parkingapp.domain.model.register.RegisterResultModel

interface RegistrationRepository {

    suspend fun registerUser(
        request: RegisterRequestModel
    ): Result<RegisterResultModel>

}