package com.aks.parkingapp.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aks.parkingapp.R
import com.aks.parkingapp.domain.model.Vehicle
import com.aks.parkingapp.presentation.ui.screens.CommonToolbar
import com.aks.parkingapp.presentation.viewmodel.VehicleViewModel

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    snackBarHostState: SnackbarHostState
) {
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {

            SnackbarHost(
                hostState = snackBarHostState
            ) { snackbarData ->

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (snackbarData.visuals.message.contains("00")) Color.Green else Color.Red
                    )
                ) {
                    Text(
                        text = snackbarData.visuals.message.split("|")[1],
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        },
        topBar = {

            CommonToolbar(
                title = stringResource(R.string.headline_home) ,
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(text = uiState.userName , style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = uiState.userEmail , style = MaterialTheme.typography.headlineMedium)




        }

    }


}
