package com.aks.parkingapp.presentation.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aks.parkingapp.presentation.navigation.Routes
import com.aks.parkingapp.presentation.ui.screens.CommonToolbar


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val uiState by viewModel
        .uiState
        .collectAsState()


    // --------------------------------
    // Observe Events
    // --------------------------------

    LaunchedEffect(Unit) {

        viewModel.event.collect { event ->

            when(event) {

                RegisterUiEvent.NavigateToOtp -> {

                    navController.navigate(
                        Routes.verifyOtpRoute(
                            uiState.fullMobileNumber
                        )
                    )
                }

                is RegisterUiEvent.ShowError -> {

                    // Snackbar later
                }
            }
        }
    }

    val buttonAlpha by animateFloatAsState(
        targetValue = if (uiState.isValidMobile) 1f else 0.5f,
        label = ""
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {

            CommonToolbar(
                title = "Sign up",
                showBackButton = false
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .navigationBarsPadding()
                .imePadding()
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically()
            ) {

                Column {

                    Text(
                        text = "Verify your\nmobile number",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 38.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = buildAnnotatedString {

                            append("We have send you an ")

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {

                                append("One Time Password (OTP)")
                            }

                            append(" on this mobile number")
                        },
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Enter mobile no.",
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                CountryCodeDropDown(

                    selectedCode = uiState.countryCode,

                    onCodeSelected = {
                        viewModel.onCountryCodeChanged(it)
                    }
                )

                OutlinedTextField(

                    value = uiState.mobileNumber,

                    onValueChange = {

                        viewModel.onMobileChanged(it)
                    },

                    modifier = Modifier.weight(1f),

                    placeholder = {

                        Text("0000000000")
                    },

                    singleLine = true,

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    shape = RoundedCornerShape(14.dp),

                    isError = uiState.mobileNumber.isNotEmpty() &&
                            !uiState.isValidMobile
                )
            }

            AnimatedVisibility(
                visible = uiState.mobileNumber.isNotEmpty() &&
                        !uiState.isValidMobile
            ) {

                Text(
                    text = "Please enter valid 10 digit number",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Already have an account? ",
                    color = Color.Gray
                )

                Text(
                    text = "Log in",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(

                onClick = {
                    viewModel.registerUser()
                },

                enabled = uiState.isValidMobile &&
                        !uiState.isLoading,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .alpha(buttonAlpha),

                shape = RoundedCornerShape(14.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {

                if (uiState.isLoading) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )

                } else {

                    Text(
                        text = "Get OTP",
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeDropDown(
    selectedCode: String,
    onCodeSelected: (String) -> Unit
) {

    val countryCodes = listOf("+91")

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,

        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        OutlinedTextField(

            value = selectedCode,

            onValueChange = {},

            readOnly = true,

            modifier = Modifier
                .menuAnchor()
                .width(100.dp),

            trailingIcon = {

                ExposedDropdownMenuDefaults
                    .TrailingIcon(
                        expanded = expanded
                    )
            },

            shape = RoundedCornerShape(14.dp),

            singleLine = true
        )

        ExposedDropdownMenu(

            expanded = expanded,

            onDismissRequest = {
                expanded = false
            }
        ) {

            countryCodes.forEach { code ->

                DropdownMenuItem(

                    text = {
                        Text(code)
                    },

                    onClick = {

                        onCodeSelected(code)

                        expanded = false
                    }
                )
            }
        }
    }
}
