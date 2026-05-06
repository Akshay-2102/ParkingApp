package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.model.Vehicle
import com.aks.parkingapp.domain.repository.VehicleRepository
import javax.inject.Inject

class AddVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicle: Vehicle) {
        repository.insert(vehicle)
    }
}