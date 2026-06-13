package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.domain.model.login.LoginRequestModel
import com.aks.parkingapp.domain.model.login.LoginResultModel
import com.aks.parkingapp.domain.model.register.RegisterRequestModel
import com.aks.parkingapp.domain.model.register.RegisterResultModel

interface LoginRepository {

    suspend fun loginUser(
        request: LoginRequestModel
    ): Result<LoginResultModel>

}