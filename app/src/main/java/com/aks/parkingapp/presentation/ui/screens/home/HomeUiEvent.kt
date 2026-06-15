package com.aks.parkingapp.presentation.ui.screens.home

sealed class HomeUiEvent {

   data object NavigateToDashboard : HomeUiEvent()

    data class ShowSuccess(
        val message: String
    ) : HomeUiEvent()

    data class ShowError(
        val message: String
    ) : HomeUiEvent()

}