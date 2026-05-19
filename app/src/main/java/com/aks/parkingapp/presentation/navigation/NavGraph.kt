package com.aks.parkingapp.presentation.navigation

import OnboardingScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aks.parkingapp.presentation.ui.screens.HomeScreen
import com.aks.parkingapp.presentation.ui.screens.LoginScreen
import com.aks.parkingapp.presentation.ui.screens.signup.RegisterScreen
import com.aks.parkingapp.presentation.ui.screens.splash.SplashScreen
import com.aks.parkingapp.presentation.ui.screens.validateOTP.ValidateOTPScreen
import com.aks.parkingapp.utils.Constants

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        enterTransition = {
            fadeIn(animationSpec = tween(500))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500))
        }
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onFinish =  {
                    navController.navigate(Routes.REGISTER) {
                        popUpTo("onboarding") { inclusive = true }
                    }
                 }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.VERIFY_OTP,
            arguments = listOf(
            navArgument(Constants.MOBILE_NO) {
                type = NavType.StringType
            }
        )) { backStackEntry ->

            val mobileNo =
                backStackEntry.arguments?.getString(Constants.MOBILE_NO) ?: ""

            ValidateOTPScreen(
                navController = navController,
                mobileNo = mobileNo
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }
        composable(Routes.HOME) {
            HomeScreen(navController)
        }


    }
}