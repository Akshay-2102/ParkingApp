package com.aks.parkingapp.presentation.ui.screens.register

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.aks.parkingapp.presentation.navigation.Routes

@Composable
fun RegisterRoute(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    val snackBarHostState =
        remember {
            SnackbarHostState()
        }

    val lifecycleOwner =
        LocalLifecycleOwner.current

    LaunchedEffect(Unit) {

        lifecycleOwner.repeatOnLifecycle(
            state = androidx.lifecycle.Lifecycle.State.STARTED
        ){
            viewModel.event.collect { event ->

                when(event){
                    RegisterUiEvent.NavigateToLogin -> {
                        navController.navigate(
                            Routes.LOGIN,
                        ){
                            popUpTo(
                                Routes.REGISTER
                            ) {
                                inclusive = true
                            }
                        }
                    }

                    is RegisterUiEvent.ShowSuccess -> {
                        snackBarHostState.showSnackbar(
                            message = "00|" + event.message
                        )
                    }

                    is RegisterUiEvent.ShowError -> {
                        snackBarHostState.showSnackbar(
                            message = "01|" + event.message
                        )
                    }



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

        snackBarHostState = snackBarHostState,

        onRegisterClick = {
            viewModel.registerUser()
        }
    )
}