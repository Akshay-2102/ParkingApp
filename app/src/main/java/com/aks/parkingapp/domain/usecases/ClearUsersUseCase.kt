package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class ClearUsersUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
   /* suspend operator fun invoke() {
        repository.clearUsers()
    }*/
}