package com.aks.parkingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.domain.model.Vehicle
import com.aks.parkingapp.domain.usecases.AddVehicleUseCase
import com.aks.parkingapp.domain.usecases.DeleteVehicleUseCase
import com.aks.parkingapp.domain.usecases.GetVehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val addVehicle: AddVehicleUseCase,
    private val deleteVehicle: DeleteVehicleUseCase,
    private val getVehicles: GetVehiclesUseCase
): ViewModel(){

    val vehicles = getVehicles()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun add(vehicle: Vehicle) {
        viewModelScope.launch {
            addVehicle(vehicle)
        }
    }

    fun delete(vehicle: Vehicle) {
        viewModelScope.launch {
            deleteVehicle(vehicle)
        }
    }

}