package com.aks.parkingapp.presentation.ui.screens.login

import com.aks.parkingapp.presentation.ui.screens.register.RegisterUiEvent

sealed class LoginUiEvent {

    data object NavigateToDashboard : LoginUiEvent()

    data class ShowSuccess(
        val message: String
    ) : LoginUiEvent()

    data class ShowError(
        val message: String
    ) : LoginUiEvent()

}