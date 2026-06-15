package com.aks.parkingapp.presentation.ui.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aks.parkingapp.presentation.ui.screens.CommonToolbar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aks.parkingapp.R
import com.aks.parkingapp.presentation.ui.screens.register.RegisterScreen
import com.aks.parkingapp.presentation.ui.screens.register.RegisterUiState


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    snackBarHostState: SnackbarHostState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit
) {

    val buttonAlpha by animateFloatAsState(
        targetValue = if (uiState.isValidEmail && uiState.isValidPassword) 1f else 0.5f,
        label = ""
    )

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val emailFocusRequester = remember {
        FocusRequester()
    }

    val passwordFocusRequester = remember {
        FocusRequester()
    }

    val keyboardController =
        LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {

            SnackbarHost(

                hostState = snackBarHostState

            ) { snackbarData ->

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (snackbarData.visuals.message.contains("00")) Color.Green else Color.Red
                    )
                ) {

                    Text(
                        text = snackbarData.visuals.message.split("|")[1],
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        },
        topBar = {

            CommonToolbar(
                title = stringResource(R.string.headline_sign_in) ,
                showBackButton = false
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Text(
                text = stringResource(R.string.label_welcome_back),
                style = MaterialTheme.typography.bodyMedium,
            )


            Image(
                painter = painterResource(
                    R.drawable.logo
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(160.dp)
            )


            OutlinedTextField(

                value = uiState.emailAddress,

                onValueChange = {
                    onEmailChanged(it)
                },
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon")
                },

                placeholder = {
                    Text(stringResource(R.string.hint_enter_email_address))
                },

                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp)
                    .focusRequester(emailFocusRequester),
                isError = uiState.emailAddress.isNotEmpty() &&
                        !uiState.isValidEmail
            )

            AnimatedVisibility(
                visible = uiState.emailAddress.isNotEmpty() &&
                        !uiState.isValidEmail
            ) {
                Text(
                    text = "Please enter a valid email",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            OutlinedTextField(

                value = uiState.password,

                onValueChange = {
                    onPasswordChanged(it)
                },
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Lock Icon")
                },
                trailingIcon = {

                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {

                        Icon(
                            imageVector =
                                if (passwordVisible)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,

                            contentDescription = null
                        )
                    }
                },

                placeholder = {
                    Text(stringResource(R.string.hint_enter_password))
                },
                visualTransformation =
                    if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp)
                    .focusRequester(passwordFocusRequester),
                isError = uiState.password.isNotEmpty() &&
                        !uiState.isValidPassword
            )

            AnimatedVisibility(
                visible = uiState.password.isNotEmpty() &&
                        !uiState.isValidPassword
            ) {
                Text(
                    text = "Please enter valid password",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.hint_forgot_password),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Blue,
                    modifier = Modifier.clickable{
                        // Click event handle
                    })
            }
            


            Spacer(modifier = Modifier.weight(1f))

            Button(

                onClick = {

                    keyboardController?.hide()
                    onLoginClick()
                },

                enabled = uiState.isValidEmail &&
                        uiState.isValidPassword,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
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
                        text = stringResource(R.string.headline_sign_up),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))



        }
    }

    if (uiState.isLoading) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.3f)
                ) .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    // consume all clicks
                },
            contentAlignment = Alignment.Center
        ) {

            Card(
                shape = RoundedCornerShape(12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator()

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text("Please wait...")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {

        LoginScreen(
            uiState = LoginUiState(),
            snackBarHostState = SnackbarHostState(),
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClick = {}
        )
}