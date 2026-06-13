package com.aks.parkingapp.presentation.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aks.parkingapp.presentation.ui.screens.CommonToolbar
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aks.parkingapp.R
import com.aks.parkingapp.presentation.ui.screens.register.RegisterScreen
import com.aks.parkingapp.presentation.ui.screens.register.RegisterUiState


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    snackBarHostState: SnackbarHostState,
) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            CommonToolbar(
                title = stringResource(R.string.headline_sign_in) ,
                showBackButton = true
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
                .imePadding(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = "Login Screen" )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {

    LoginScreen(
        uiState = LoginUiState(),
        snackBarHostState = SnackbarHostState(),


    )
}