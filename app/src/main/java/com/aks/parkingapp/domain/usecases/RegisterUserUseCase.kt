package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.model.register.RegisterRequestModel
import com.aks.parkingapp.domain.model.register.RegisterResultModel
import com.aks.parkingapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    // Api call
    suspend operator fun invoke(
        request: RegisterRequestModel
    ): Result<RegisterResultModel> {

        return repository.registerUser(
            request
        )
    }

}