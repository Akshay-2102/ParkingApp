package com.aks.parkingapp.presentation.ui.screens.validateOTP

import com.aks.parkingapp.presentation.ui.screens.signup.RegisterUiEvent

sealed class OtpUiEvent {

    data object NavigateToOtp :
        OtpUiEvent()

    data class ShowError(
        val message: String
    ) : OtpUiEvent()
}