package com.aks.parkingapp.presentation.ui.screens.register

sealed class RegisterUiEvent {

    data object NavigateToLogin : RegisterUiEvent()

    data class ShowSuccess(
        val message: String
    ) : RegisterUiEvent()

    data class ShowError(
        val message: String
    ) : RegisterUiEvent()
}