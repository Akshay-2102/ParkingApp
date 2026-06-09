package com.aks.parkingapp.presentation.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aks.parkingapp.presentation.ui.screens.CommonToolbar
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.aks.parkingapp.R


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onFullNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onMobileChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
    onRegisterClick: () -> Unit
) {
    val buttonAlpha by animateFloatAsState(
        targetValue = if (uiState.isValidMobile) 1f else 0.5f,
        label = ""
    )

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    var isChecked by remember {
        mutableStateOf(false)
    }

    val nameFocusRequester = remember {
        FocusRequester()
    }

    val emailFocusRequester = remember {
        FocusRequester()
    }

    val mobileFocusRequester = remember {
        FocusRequester()
    }

    val passwordFocusRequester = remember {
        FocusRequester()
    }

    val confirmPasswordFocusRequester = remember {
        FocusRequester()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        snackbarHost = {

            SnackbarHost(

                hostState = snackbarHostState

            ) { snackbarData ->

                Card(

                    shape = RoundedCornerShape(12.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.Red
                    )

                ) {

                    Text(

                        text = snackbarData.visuals.message,

                        color = Color.White,

                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        },

        topBar = {

            CommonToolbar(
                title = stringResource(R.string.headline_sign_up) ,
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.label_create_your_account),
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
                value = uiState.fullName,
                onValueChange = {
                    onFullNameChanged(it)
                },
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Person Icon")
                },
                placeholder = {
                    Text(text = stringResource(R.string.hint_enter_full_name))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization =
                        KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        emailFocusRequester.requestFocus()
                    }
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .focusRequester(nameFocusRequester),
                isError = uiState.fullName.isNotEmpty() &&
                        !uiState.isValidFullName
            )

            AnimatedVisibility(
                visible = uiState.fullName.isNotEmpty() &&
                        !uiState.isValidFullName
            ) {
                Text(
                    text = "Please enter a valid full name",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

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
                        mobileFocusRequester.requestFocus()
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

                value = uiState.mobileNumber,

                onValueChange = {

                    onMobileChanged(it)
                },
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Call, contentDescription = "Call Icon")
                },

                placeholder = {
                    Text(stringResource(R.string.hint_enter_mobile_number))
                },

                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
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
                    .focusRequester(mobileFocusRequester),
                isError = uiState.mobileNumber.isNotEmpty() &&
                        !uiState.isValidMobile
            )

            AnimatedVisibility(
                visible = uiState.mobileNumber.isNotEmpty() &&
                        !uiState.isValidMobile
            ) {
                Text(
                    text = "Please enter a valid mobile number",
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
                            imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        confirmPasswordFocusRequester.requestFocus()
                    }
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
                    text = "Min 8 chars, uppercase, lowercase, number & special character",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }


            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            OutlinedTextField(

                value = uiState.confirmPassword,

                onValueChange = {

                    onConfirmPasswordChanged(it)
                },
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Lock Icon")

                },

                trailingIcon = {
                    IconButton(
                        onClick = {
                            confirmPasswordVisible = !confirmPasswordVisible
                        }
                    ) {

                        Icon(
                            imageVector =
                                if (confirmPasswordVisible)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,

                            contentDescription = null
                        )
                    }
                },
                visualTransformation =
                    if (confirmPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                placeholder = {
                    Text(stringResource(R.string.hint_enter_confirm_password))
                },

                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp)
                    .focusRequester(confirmPasswordFocusRequester),
                isError = uiState.confirmPassword.isNotEmpty() &&
                        !uiState.isPasswordMatched
            )

            AnimatedVisibility(
                visible = uiState.confirmPassword.isNotEmpty() &&
                        !uiState.isPasswordMatched
            ) {
                Text(
                    text = "Password does not match",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isChecked = !isChecked
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = null
                )

                Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                Text(
                    text = buildAnnotatedString {

                        append(stringResource(R.string.hint_teams_and_condition))
                        append(" ")

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.Blue
                            )
                        ) {
                            append(stringResource(R.string.hint_tnc))
                        }
                    },
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            Button(

                onClick = {
                    onRegisterClick()
                },

                enabled = uiState.isValidFullName &&
                          uiState.isValidEmail &&
                          uiState.isValidPassword &&
                          uiState.isPasswordMatched,

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

            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {

    RegisterScreen(
        uiState = RegisterUiState(),
        onFullNameChanged = {},
        onEmailChanged={},
        onMobileChanged = {},
        onPasswordChanged = {},
        onConfirmPasswordChanged = {},
        snackbarHostState = SnackbarHostState(),
        onRegisterClick = {}

    )
}