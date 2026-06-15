package com.aks.parkingapp.data.repository

import com.aks.parkingapp.data.local.preferences.PreferencesManager
import com.aks.parkingapp.data.mapper.toLoginRequestDTO
import com.aks.parkingapp.data.mapper.toLoginResult
import com.aks.parkingapp.data.remote.ApiService
import com.aks.parkingapp.domain.model.login.LoginRequestModel
import com.aks.parkingapp.domain.model.login.LoginResultModel
import com.aks.parkingapp.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val preferenceManager: PreferencesManager
) : LoginRepository {
    override suspend fun loginUser(request: LoginRequestModel): Result<LoginResultModel> {
        return try {

            val dto = request.toLoginRequestDTO()
            val response = api.loginUser(dto)
            if (response.success){
                // Register Successful flag change store data in preference
                preferenceManager.setToken("Bearer " + response.accessToken)
            }
            Result.success(response.toLoginResult())

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}