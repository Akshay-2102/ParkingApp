package com.aks.parkingapp.data.repository

import com.aks.parkingapp.data.local.dao.VehicleDao
import com.aks.parkingapp.data.mapper.toDomain
import com.aks.parkingapp.data.mapper.toEntity
import com.aks.parkingapp.domain.model.Vehicle
import com.aks.parkingapp.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val dao: VehicleDao
) : VehicleRepository {

    override suspend fun insert(vehicle: Vehicle) {
        dao.insert(vehicle.toEntity())
    }

    override suspend fun delete(vehicleId: Int) {
        dao.deleteVehicleById(vehicleId)
    }

    override suspend fun update(
        vehicleNo: String,
        vehicleType: String,
        vehicleId: Int
    ) {
       // TODO("Not yet implemented")
    }

   /* override suspend fun updateVehicleById(vehicleNo:String, vehicaleType:Int, vehicaleId: Int){
        dao.updateVehicleById(vehicleNo,vehicaleType,vehicaleId)
    }*/

    override fun getAll(): Flow<List<Vehicle>> {
        return dao.getAllVehicles().map { list ->
            list.map { it.toDomain() }
        }
    }
}