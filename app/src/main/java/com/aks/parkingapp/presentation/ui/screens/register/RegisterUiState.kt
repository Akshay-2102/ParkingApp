package com.aks.parkingapp.presentation.ui.screens.register

data class RegisterUiState(
    val fullName: String = "",
    val emailAddress: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val responseMessage: String = "",
    val error: String? = null
) {

    val isValidFullName: Boolean
        get() = fullName.length > 6

    val isValidMobile: Boolean
        get() = mobileNumber.length == 10

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

    val isPasswordMatched: Boolean
        get() =
            password == confirmPassword

}
