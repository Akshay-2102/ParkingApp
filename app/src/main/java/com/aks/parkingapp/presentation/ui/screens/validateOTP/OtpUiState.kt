package com.aks.parkingapp.presentation.ui.screens.validateOTP

import com.aks.parkingapp.domain.model.User

data class OtpUiState(
    val users: List<User> = emptyList(),
    val otp: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) {

    val isValidOtp: Boolean
        get() = otp.length == 6

}