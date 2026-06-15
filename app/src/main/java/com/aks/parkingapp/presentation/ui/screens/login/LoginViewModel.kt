package com.aks.parkingapp.presentation.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.domain.model.login.LoginRequestModel
import com.aks.parkingapp.domain.model.login.LoginResultModel
import com.aks.parkingapp.domain.model.register.RegisterRequestModel
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


    fun onEmailChange(
        email: String
    ){
        _uiState.update {
            it.copy(emailAddress = email)
        }
    }

    fun onPasswordChange(
        password: String
    ){
        _uiState.update {
            it.copy(password = password)
        }
    }





    // --------------------------------
    // Login User
    // --------------------------------

    fun loginUser(){
        viewModelScope.launch {


            try {

                _uiState.update {

                    it.copy(
                        isLoading = true
                    )
                }

                val reqLogin = LoginRequestModel(
                    _uiState.value.emailAddress,
                    _uiState.value.password
                )

                val result =
                    loginUseCase(
                        reqLogin
                    )

                result.onSuccess { response ->

                    if (response.responseCode == "00") {

                        _event.emit(
                            LoginUiEvent.ShowSuccess(
                                response.responseMessage ?: "Success"
                            )
                        )

                        _event.emit(
                            LoginUiEvent.NavigateToDashboard
                        )
                    }else{
                        _event.emit(
                            LoginUiEvent.ShowError(
                                response.responseMessage ?: "Error"
                            )
                        )
                    }

                }.onFailure {

                    _event.emit(
                        LoginUiEvent.ShowError(
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
                    LoginUiEvent.ShowError(
                        e.message ?: "Unknown error"
                    )
                )
            }

        }

    }

}