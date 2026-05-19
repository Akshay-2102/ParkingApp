package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.repository.OtpRepository
import javax.inject.Inject

class UpdateOtpUseCase @Inject constructor(
    val repository: OtpRepository
) {

    suspend operator fun invoke(
        mobile: String,
        otp: String,
    ) {

        repository.updateOtp(mobile,otp)
    }

}