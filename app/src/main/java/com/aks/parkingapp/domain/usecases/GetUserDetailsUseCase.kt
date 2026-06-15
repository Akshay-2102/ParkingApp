package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.model.userDetails.UserDetailsModel
import com.aks.parkingapp.domain.repository.HomeRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: HomeRepository
){

    // Api call
    suspend operator fun invoke(): Result<UserDetailsModel> {
        return repository.getUserDetails()
    }


}