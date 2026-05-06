package com.aks.parkingapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.aks.parkingapp.ui.theme.ParkingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.aks.parkingapp.presentation.navigation.AppNavGraph


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkingAppTheme {
                SetStatusBarColor()
                AppNavGraph()
            }
        }
    }
}

@Composable
fun SetStatusBarColor() {
    val activity = LocalContext.current as Activity
    val window = activity.window

    SideEffect {
        window.statusBarColor = Color.Transparent.toArgb()

        WindowCompat.getInsetsController(window, window.decorView)?.apply {
            isAppearanceLightStatusBars = true
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkingAppTheme {
        AppNavGraph()
    }
}