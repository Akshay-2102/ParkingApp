package com.aks.parkingapp.data.repository

import com.aks.parkingapp.data.local.dao.UserDao
import com.aks.parkingapp.data.mapper.toDomain
import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.repository.OtpRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OtpRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): OtpRepository {

    override suspend fun updateOtp(mobileNo: String, otp: String) {
        delay(3000)
        userDao.updateOtp(mobileNo,otp)
    }

    override suspend fun updateIsSignupCompleted(mobileNo: String,isSignupCompleted: Boolean) {
        delay(3000)
        userDao.updateSignUpCompleted(mobileNo, isSignupCompleted)
    }

    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers()
            .map { users ->

                users.map {
                    it.toDomain()
                }
            }
    }


}