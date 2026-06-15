package com.aks.parkingapp.presentation.ui.screens.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.model.register.RegisterRequestModel
import com.aks.parkingapp.domain.usecases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    // --------------------------------
    // STATE FLOW
    // --------------------------------

    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiState = _uiState.asStateFlow()

    // --------------------------------
    // SHARED FLOW
    // --------------------------------

    private val _event = MutableSharedFlow<RegisterUiEvent>()
    val event = _event.asSharedFlow()

    // --------------------------------
    // Update Mobile
    // --------------------------------

    fun onFullNameChange(
        fullName: String
    ){
        if (
            fullName.length <= 50 &&
            fullName.all {

                it.isLetter() ||
                        it.isWhitespace() ||
                        it == '.' ||
                        it == '-' ||
                        it == '\''
            }
            ){
            _uiState.update {
                it.copy(fullName = fullName)
            }
        }
    }


    fun onEmailChange(
        email: String
    ){
        _uiState.update {
            it.copy(emailAddress = email)
        }
    }

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


    fun onPasswordChange(
        password: String
    ){
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun onConfirmPasswordChange(
        confirmPassword: String
    ){
        _uiState.update {
            it.copy(confirmPassword = confirmPassword)
        }
    }


    // --------------------------------
    // Register UserDTO
    // --------------------------------
    fun registerUser() {

        viewModelScope.launch {

            try {

                _uiState.update {

                    it.copy(
                        isLoading = true
                    )
                }

                val reqRegister = RegisterRequestModel(
                    _uiState.value.fullName,
                    _uiState.value.emailAddress,
                    _uiState.value.mobileNumber,
                    _uiState.value.password
                )

                val result =
                    registerUserUseCase(
                        reqRegister
                    )

                result.onSuccess { response ->

                    if (response.responseCode == "00") {

                        _event.emit(
                            RegisterUiEvent.ShowSuccess(
                                response.responseMessage ?: "Success"
                            )
                        )

                        _event.emit(
                            RegisterUiEvent.NavigateToLogin
                        )
                    }else{
                        _event.emit(
                            RegisterUiEvent.ShowError(
                                response.responseMessage ?: "Error"
                            )
                        )
                    }

                }.onFailure {

                    _event.emit(
                        RegisterUiEvent.ShowError(
                            it.message ?: "Error"
                        )
                    )
                }


                _uiState.update {

                    it.copy(
                        isLoading = false
                    )
                }

              /*  _event.emit(
                    RegisterUiEvent.NavigateToLogin
                )*/

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