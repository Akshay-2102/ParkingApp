package com.aks.parkingapp.presentation.ui.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonToolbar(
    title: String,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {

    TopAppBar(

        title = {
            Text(
                text = title, style = TextStyle(color = Color.White,  fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        },

        navigationIcon = {

            if (showBackButton) {

                IconButton(
                    onClick = {
                        onBackClick()
                    }
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },

        actions = actions,

        colors = TopAppBarDefaults.topAppBarColors(

            containerColor = MaterialTheme.colorScheme.primary,

            titleContentColor = MaterialTheme.colorScheme.onPrimary,

            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,

            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}