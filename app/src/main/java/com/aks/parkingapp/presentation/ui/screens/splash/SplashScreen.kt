package com.aks.parkingapp.presentation.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aks.parkingapp.R
import com.aks.parkingapp.presentation.navigation.Routes

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    val destination by viewModel
        .startDestination
        .collectAsState()

    val scale = remember {
        Animatable(0.6f)
    }

    val alpha = remember {
        Animatable(0f)
    }

    // Animation
    LaunchedEffect(Unit) {

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = EaseOutBack
            )
        )

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
    }

    // Navigation
    LaunchedEffect(destination) {

        destination?.let {

            navController.navigate(it) {

                popUpTo(Routes.SPLASH) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                R.drawable.logo
            ),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
                .size(250.dp)
        )
    }
}