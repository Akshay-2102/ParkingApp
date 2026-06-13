package com.aks.parkingapp.presentation.ui.screens.login

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.aks.parkingapp.presentation.navigation.Routes

@Composable
fun LoginRoute(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){

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
            state = Lifecycle.State.STARTED
        ){
            viewModel.event.collect { event ->

                when(event){

                    LoginUiEvent.NavigateToDashboard -> {
                        navController.navigate(
                            Routes.HOME
                        )
                    }

                    is LoginUiEvent.ShowSuccess -> {
                        snackBarHostState.showSnackbar(
                            message = "00|" + event.message
                        )
                    }

                    is LoginUiEvent.ShowError -> {
                        snackBarHostState.showSnackbar(
                            message = "01|" + event.message
                        )
                    }

                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState
    )


}