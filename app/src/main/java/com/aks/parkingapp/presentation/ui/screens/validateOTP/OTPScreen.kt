package com.aks.parkingapp.presentation.ui.screens.validateOTP

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aks.parkingapp.presentation.ui.screens.CommonToolbar
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.aks.parkingapp.utils.maskMobileNumber


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidateOTPScreen(
    navController: NavController,
    viewModel: OtpViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    val focusRequesters = List(6) {
        FocusRequester()
    }

    val buttonAlpha by animateFloatAsState(
        targetValue = if (uiState.isValidOtp) 1f else 0.5f,
        label = ""
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonToolbar(
                title = "Verify OTP",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->

        Column( modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 24.dp)
            .navigationBarsPadding()
            .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            LazyColumn {

                items(uiState.users) { user ->


                    Spacer(modifier = Modifier.height(20.dp))

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically()
                    ) {

                        Column {

                            Text(
                                text = "OTP Verification",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 38.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = buildAnnotatedString {

                                    append("Your OTP has been sent to ")

                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append(user.mobileNumber.maskMobileNumber())
                                    }

                                    append(" this mobile number")
                                },
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Spacer(modifier = Modifier.padding(16.dp))

                    Text(
                        text = user.otp
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    OtpInputField(

                        otpValue = uiState.otp,

                        onOtpChange = {

                            viewModel.onOtpChanged(it)
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(

                        onClick = {

                            viewModel.validateOtp(
                              //  actualOtp = user.otp
                            )
                        },

                        enabled = uiState.isValidOtp &&
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

                        Text(
                            text = "Verify OTP"
                        )
                    }

                }
            }



        }


    }


}

@Composable
fun OtpInputField(

    otpValue: String,

    onOtpChange: (String) -> Unit
) {

    val focusRequester = remember {
        FocusRequester()
    }

    var selectedIndex by remember {
        mutableIntStateOf(-1)
    }

    Box(

        modifier = Modifier.fillMaxWidth(),

        contentAlignment = Alignment.Center
    ) {

        BasicTextField(

            value = otpValue,

            onValueChange = { input ->

                // BACKSPACE
                if (input.length < otpValue.length) {

                    onOtpChange(input)

                    return@BasicTextField
                }

                // NEW INPUT
                if (
                    input.isNotEmpty() &&
                    input.all(Char::isDigit)
                ) {

                    val otpChars =
                        otpValue
                            .padEnd(6, ' ')
                            .toMutableList()

                    val newChar =
                        input.last()

                    // Replace selected position
                    if (selectedIndex != -1) {

                        otpChars[selectedIndex] =
                            newChar

                        selectedIndex = -1

                    } else {

                        // Append next empty
                        val emptyIndex =
                            otpChars.indexOfFirst {
                                it == ' '
                            }

                        if (emptyIndex != -1) {

                            otpChars[emptyIndex] =
                                newChar
                        }
                    }

                    onOtpChange(
                        otpChars.joinToString("")
                            .trim()
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            modifier = Modifier
                .focusRequester(focusRequester)
                .size(1.dp)
                .alpha(0f)
        )

        Row(

            horizontalArrangement =
                Arrangement.spacedBy(6.dp)
        ) {

            repeat(6) { index ->

                val char =
                    otpValue.getOrNull(index)
                        ?.toString() ?: ""

                val isFocused =
                    selectedIndex == index ||
                            (
                                    selectedIndex == -1 &&
                                            otpValue.length == index
                                    )

                val borderColor by animateColorAsState(

                    targetValue =
                        if (isFocused)
                            MaterialTheme.colorScheme.primary
                        else
                            Color.LightGray,

                    label = ""
                )

                val scale by animateFloatAsState(

                    targetValue =
                        if (isFocused) 1.08f else 1f,

                    label = ""
                )

                Box(

                    modifier = Modifier
                        .size(55.dp)
                        .scale(scale)
                        .border(
                            width = 2.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable {

                            selectedIndex = index

                            focusRequester.requestFocus()
                        },

                    contentAlignment = Alignment.Center
                ) {

                    Text(

                        text = char,

                        fontSize = 18.sp,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}