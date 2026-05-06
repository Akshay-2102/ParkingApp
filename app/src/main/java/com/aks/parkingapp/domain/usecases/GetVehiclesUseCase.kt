package com.aks.parkingapp.domain.usecases

import com.aks.parkingapp.domain.model.Vehicle
import com.aks.parkingapp.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehiclesUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    operator fun invoke(): Flow<List<Vehicle>> {
        return repository.getAll()
    }
}