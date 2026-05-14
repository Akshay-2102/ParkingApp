package com.aks.parkingapp.data.repository

import com.aks.parkingapp.data.local.dao.UserDao
import com.aks.parkingapp.data.local.entity.UserEntity
import com.aks.parkingapp.data.mapper.toDomain
import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun registerUser(
        mobile: String
    ) {

        delay(3000)

        userDao.insertUser(
            UserEntity(
                mobileNumber = mobile
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
}