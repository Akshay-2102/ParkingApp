package com.aks.parkingapp.presentation.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.domain.usecases.GetUserUseCase
import com.aks.parkingapp.domain.usecases.LoginUserUseCase
import com.aks.parkingapp.domain.usecases.UpdateOtpUseCase
import com.aks.parkingapp.presentation.ui.screens.register.RegisterUiEvent
import com.aks.parkingapp.presentation.ui.screens.register.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUserUseCase,
): ViewModel(){


    // --------------------------------
    // STATE FLOW
    // --------------------------------
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    // --------------------------------
    // SHARED FLOW
    // --------------------------------
    private val _event = MutableSharedFlow<LoginUiEvent>()
    val event = _event.asSharedFlow()


}