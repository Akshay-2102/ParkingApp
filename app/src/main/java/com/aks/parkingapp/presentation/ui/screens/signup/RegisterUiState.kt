package com.aks.parkingapp.presentation.ui.screens.signup

data class RegisterUiState(
    val mobileNumber: String = "",
    val countryCode: String = "+91",
    val isLoading: Boolean = false,
    val error: String? = null
) {

    val isValidMobile: Boolean
        get() = mobileNumber.length == 10

    val fullMobileNumber: String
        get() = "$countryCode $mobileNumber"
}
