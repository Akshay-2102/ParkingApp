package com.aks.parkingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aks.parkingapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(
        user: UserEntity
    )

    @Query(""" DELETE FROM users WHERE isSignupCompleted = 0 """)
    suspend fun deleteUnverifiedUsers()

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("""SELECT * FROM users WHERE mobileNumber = :mobile LIMIT 1 """)
    suspend fun getUserByMobile(mobile: String): UserEntity?

    @Query(""" UPDATE users SET otp = :otp WHERE mobileNumber = :mobile """)
    suspend fun updateOtp(mobile: String, otp: String)

    @Query(""" UPDATE users SET isSignUpCompleted = :isSignUpCompleted WHERE mobileNumber = :mobile """)
    suspend fun updateSignUpCompleted(mobile: String, isSignUpCompleted: Boolean)

}