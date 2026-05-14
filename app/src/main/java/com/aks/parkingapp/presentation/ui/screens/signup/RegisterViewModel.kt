package com.aks.parkingapp.presentation.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.domain.usecases.RegisterUserUseCase
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
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    // --------------------------------
    // STATE FLOW
    // --------------------------------

    private val _uiState =
        MutableStateFlow(RegisterUiState())

    val uiState = _uiState.asStateFlow()

    // --------------------------------
    // SHARED FLOW
    // --------------------------------

    private val _event =
        MutableSharedFlow<RegisterUiEvent>()

    val event = _event.asSharedFlow()

    // --------------------------------
    // Update Mobile
    // --------------------------------

    fun onMobileChanged(
        number: String
    ) {

        if (
            number.length <= 10 &&
            number.all(Char::isDigit)
        ) {

            _uiState.update {

                it.copy(
                    mobileNumber = number
                )
            }
        }
    }

    // --------------------------------
    // for country code
    // --------------------------------
    fun onCountryCodeChanged(code: String) {
        _uiState.update {
            it.copy(
                countryCode = code
            )
        }
    }


    // --------------------------------
    // Register User
    // --------------------------------

    fun registerUser() {

        viewModelScope.launch {

            try {

                _uiState.update {

                    it.copy(
                        isLoading = true
                    )
                }

                registerUserUseCase(
                    _uiState.value.mobileNumber
                )

                _uiState.update {

                    it.copy(
                        isLoading = false
                    )
                }

                _event.emit(
                    RegisterUiEvent.NavigateToOtp
                )

            } catch (e: Exception) {

                _uiState.update {

                    it.copy(
                        isLoading = false
                    )
                }

                _event.emit(
                    RegisterUiEvent.ShowError(
                        e.message ?: "Unknown error"
                    )
                )
            }
        }
    }
}