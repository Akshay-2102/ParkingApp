package com.aks.parkingapp.presentation.ui.screens.signup

sealed class RegisterUiEvent {

    data object NavigateToOtp : RegisterUiEvent()

    data class ShowSuccess(
        val message: String
    ) : RegisterUiEvent()

    data class ShowError(
        val message: String
    ) : RegisterUiEvent()
}