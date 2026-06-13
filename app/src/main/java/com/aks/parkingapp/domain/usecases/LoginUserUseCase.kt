package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.model.login.LoginRequestModel
import com.aks.parkingapp.domain.model.login.LoginResultModel
import com.aks.parkingapp.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    // Api call
    suspend operator fun invoke(
        request: LoginRequestModel
    ): Result<LoginResultModel> {

        return repository.loginUser(
            request
        )
    }
}