package com.aks.parkingapp.data.repository

import com.aks.parkingapp.data.local.dao.UserDao
import com.aks.parkingapp.data.local.preferences.PreferencesManager
import com.aks.parkingapp.data.local.preferences.SecureKeys
import com.aks.parkingapp.data.mapper.toRegisterRequestDTO
import com.aks.parkingapp.data.mapper.toRegisterResult
import com.aks.parkingapp.data.remote.ApiService
import com.aks.parkingapp.domain.model.register.RegisterRequest
import com.aks.parkingapp.domain.model.register.RegisterResult
import com.aks.parkingapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val api: ApiService,
    private val preferenceManager: PreferencesManager
) : RegistrationRepository {

    override suspend fun registerUser(request: RegisterRequest): Result<RegisterResult> {

        return try {

            val dto = request.toRegisterRequestDTO()

            val response = api.registerUser(dto)

            // Register Successful flag change store data in preference
            preferenceManager.setRegistrationCompleted(true)

            Result.success(response.toRegisterResult())

        } catch (e: Exception) {

            Result.failure(e)
        }

    }

   /* override suspend fun registerUser(
        user: User
    ) {

        delay(3000)

        userDao.insertUser(
            UserEntity(
                mobileNumber = user.mobileNumber,
                otp = user.otp,
                isSignupCompleted = user.isSignupCompleted
            )
        )
    }

    override fun getUsers(): Flow<List<User>> {

        return userDao.getUsers()
            .map { users ->

                users.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun clearUsers() {
        userDao.deleteUnverifiedUsers()
    }*/

}