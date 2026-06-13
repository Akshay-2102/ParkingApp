package com.aks.parkingapp.presentation.ui.screens.login

import com.aks.parkingapp.domain.model.User

data class LoginUiState(
    val emailAddress: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val responseMessage: String = "",
    val error: String? = null
) {

    val isValidEmail: Boolean
        get() = android.util.Patterns
            .EMAIL_ADDRESS
            .matcher(emailAddress)
            .matches()

    val hasUpperCase: Boolean
        get() = password.any { it.isUpperCase() }

    val hasLowerCase: Boolean
        get() = password.any { it.isLowerCase() }

    val hasDigit: Boolean
        get() = password.any { it.isDigit() }

    val hasSpecialChar: Boolean
        get() = password.any {
            !it.isLetterOrDigit()
        }

    val hasMinLength: Boolean
        get() = password.length >= 8

    val isValidPassword: Boolean
        get() =
            hasUpperCase &&
                    hasLowerCase &&
                    hasDigit &&
                    hasSpecialChar &&
                    hasMinLength

}