package com.aks.parkingapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aks.parkingapp.domain.model.Vehicle
import com.aks.parkingapp.presentation.viewmodel.VehicleViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: VehicleViewModel = hiltViewModel()
) {

    var vehicleNo by remember { mutableStateOf("") }
    var vehicleType by remember { mutableStateOf("") }

    val vehicles by viewModel.vehicles.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = vehicleNo,
            onValueChange = { vehicleNo = it },
            label = { Text("VehicleEntity No") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = vehicleType,
            onValueChange = { vehicleType = it },
            label = { Text("VehicleEntity Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (vehicleNo.isNotEmpty() && vehicleType.isNotEmpty()) {

                    val currentTimeMillis: Long = System.currentTimeMillis()

                    val vehicleData = Vehicle(0,
                        vehicleNo.toInt(), vehicleType.toInt(), currentTimeMillis
                    )

                    viewModel.add(vehicleData)

                    vehicleNo = ""
                    vehicleType = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add VehicleEntity")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Vehicles List:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(vehicles) { vehicle ->
                Text("No:: ${vehicle.vehicleNo} - Type:: ${vehicle.vehicleType} - DT:: ${vehicle.timestamp}")
                Button(onClick = {
                    viewModel.delete(vehicle)
                }) {
                    Text("Delete")
                }
                Divider()
            }
        }
    }


}
