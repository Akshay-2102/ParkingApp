package com.aks.parkingapp.data.mapper

import com.aks.parkingapp.data.local.entity.UserEntity
import com.aks.parkingapp.domain.model.User


fun UserEntity.toDomain(): User {

    return User(
        mobileNumber = mobileNumber,
        otp = otp,
        isSignupCompleted = isSignupCompleted
    )
}

fun User.toEntity(): UserEntity {

    return UserEntity(
        mobileNumber = mobileNumber,
        otp = otp,
        isSignupCompleted = isSignupCompleted
    )
}