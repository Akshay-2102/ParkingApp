package com.aks.parkingapp.presentation.ui.screens.signup

import com.aks.parkingapp.domain.model.register.RegisterResult

data class RegisterUiState(
    val mobileNumber: String = "",
    val countryCode: String = "+91",
    val isLoading: Boolean = false,
    val responseMessage: String = "",
    val error: String? = null
) {

    val isValidMobile: Boolean
        get() = mobileNumber.length == 10

    val fullMobileNumber: String
        get() = "$countryCode $mobileNumber"
}
