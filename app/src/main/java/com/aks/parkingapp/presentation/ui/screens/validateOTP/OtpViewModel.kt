package com.aks.parkingapp.presentation.ui.screens.validateOTP

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.domain.usecases.GetUserUseCase
import com.aks.parkingapp.domain.usecases.GetVehiclesUseCase
import com.aks.parkingapp.domain.usecases.UpdateOtpUseCase
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
class OtpViewModel @Inject constructor(
    val updateOtpUseCase: UpdateOtpUseCase,
    val getUserUseCase: GetUserUseCase
    ) : ViewModel() {

    // --------------------------------
    // STATE FLOW
    // --------------------------------

    private val _uiState =
        MutableStateFlow(OtpUiState())

    val uiState = _uiState.asStateFlow()


    // --------------------------------
    // SHARED FLOW
    // --------------------------------

    private val _event =
        MutableSharedFlow<OtpUiEvent>()

     val event = _event.asSharedFlow()

    init {
        getUserDetails()
    }


    // --------------------------------
    // Get User details
    // --------------------------------
    fun getUserDetails(){
        viewModelScope.launch {

            getUserUseCase().collect{ users ->

                _uiState.update {
                    it.copy(
                        users = users
                    )
                }
            }
        }
    }


    // --------------------------------
    // Check OTP
    // --------------------------------
    fun onOtpChanged(
        otp: String
    ) {

        if (
            otp.length <= 6 &&
            otp.all(Char::isDigit)
        ) {

            _uiState.update {

                it.copy(
                    otp = otp,
                    error = null
                )
            }
        }
    }


    // --------------------------------
    // Validate OTP
    // --------------------------------
    fun validateOtp() {

        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    isLoading = true
                )
            }

            delay(1500)

            if (_uiState.value.otp == _uiState.value.users[0].otp) {

                // Success
                _uiState.update {

                    it.copy(
                        isLoading = false
                    )
                }

            } else {

                _uiState.update {

                    it.copy(
                        isLoading = false,
                        error = "Invalid OTP"
                    )
                }
            }
        }
    }



}