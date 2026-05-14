package com.aks.parkingapp.presentation.navigation

object Routes {

    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val REGISTER = "register"
    const val VERIFY_OTP = "verify_otp/{mobileNo}"
    const val LOGIN = "login"
    const val HOME = "home"


    // For data transfer
    fun verifyOtpRoute(mobileNo: String) =
        "verify_otp/$mobileNo"



}