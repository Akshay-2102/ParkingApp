package com.aks.parkingapp.domain.repository

import com.aks.parkingapp.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun insert(vehicle: Vehicle)
    suspend fun delete(vehicleId: Int)
    suspend fun update(vehicleNo: String,vehicleType: String,vehicleId: Int)
    fun getAll(): Flow<List<Vehicle>>
}