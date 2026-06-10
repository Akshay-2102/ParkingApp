package com.aks.parkingapp.presentation.ui.screens.signup

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aks.parkingapp.presentation.navigation.Routes

@Composable
fun RegisterRoute(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val uiState by viewModel
        .uiState
        .collectAsState()

    val snackbarHostState =
        remember {
            SnackbarHostState()
        }

    LaunchedEffect(Unit) {

        viewModel.event.collect { event ->

            when(event){
                RegisterUiEvent.NavigateToOtp -> {
                    navController.navigate(
                        Routes.VERIFY_OTP
                    )
                }
                is RegisterUiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is RegisterUiEvent.ShowSuccess -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

            }
        }
    }


    RegisterScreen(

        uiState = uiState,

        onFullNameChanged = {
            viewModel.onFullNameChange(it)
        },

        onEmailChanged ={
            viewModel.onEmailChange(it)
        },

        onMobileChanged = {
            viewModel.onMobileChanged(it)
        },

        onPasswordChanged = {
            viewModel.onPasswordChange(it)
        },

        onConfirmPasswordChanged = {
            viewModel.onConfirmPasswordChange(it)
        },

        snackbarHostState = snackbarHostState,

        onRegisterClick = {
            viewModel.registerUser()
        }
    )
}