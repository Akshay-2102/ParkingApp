package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        mobile: String
    ) {

        repository.registerUser(mobile)
    }
}