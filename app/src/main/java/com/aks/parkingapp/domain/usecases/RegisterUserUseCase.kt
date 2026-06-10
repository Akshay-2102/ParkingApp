package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.data.remote.registerDTO.RegisterRequestDTO
import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.model.register.RegisterRequest
import com.aks.parkingapp.domain.model.register.RegisterResult
import com.aks.parkingapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    // Api call
    suspend operator fun invoke(
        request: RegisterRequest
    ): Result<RegisterResult> {

        return repository.registerUser(
            request
        )
    }

}