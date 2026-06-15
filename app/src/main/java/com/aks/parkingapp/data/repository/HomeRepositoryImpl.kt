package com.aks.parkingapp.data.repository

import com.aks.parkingapp.data.local.preferences.PreferencesManager
import com.aks.parkingapp.data.mapper.toUserDetailsResult
import com.aks.parkingapp.data.remote.ApiService
import com.aks.parkingapp.domain.model.userDetails.UserDetailsModel
import com.aks.parkingapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val preferenceManager: PreferencesManager
) : HomeRepository {

    override suspend fun getUserDetails(): Result<UserDetailsModel> {

        return try {
            val token = preferenceManager.getToken()
            val response = api.getUserDetails(token?:"")
            Result.success(response.toUserDetailsResult())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}