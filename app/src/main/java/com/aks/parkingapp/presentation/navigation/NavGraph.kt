package com.aks.parkingapp.presentation.navigation

import OnboardingScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aks.parkingapp.presentation.ui.screens.HomeScreen
import com.aks.parkingapp.presentation.ui.screens.LoginScreen
import com.aks.parkingapp.presentation.ui.screens.login.LoginRoute
import com.aks.parkingapp.presentation.ui.screens.register.RegisterRoute
import com.aks.parkingapp.presentation.ui.screens.splash.SplashScreen

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
            RegisterRoute(navController)
        }

        composable(Routes.LOGIN) {
            LoginRoute(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }


    }
}