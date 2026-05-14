package com.aks.parkingapp.presentation.ui.screens.signup

sealed class RegisterUiEvent {

    data object NavigateToOtp :
        RegisterUiEvent()

    data class ShowError(
        val message: String
    ) : RegisterUiEvent()
}