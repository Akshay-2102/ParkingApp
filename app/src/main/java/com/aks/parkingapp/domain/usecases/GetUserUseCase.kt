package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.repository.OtpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: OtpRepository
) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }

}