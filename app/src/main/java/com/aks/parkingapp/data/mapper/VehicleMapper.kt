package com.aks.parkingapp.data.mapper

import com.aks.parkingapp.data.local.entity.VehicleEntity
import com.aks.parkingapp.domain.model.Vehicle

// Entity → Domain
fun VehicleEntity.toDomain(): Vehicle {
    return Vehicle(
        id = id,
        vehicleNo = vehicleNo,
        vehicleType = vehicleType,
        timestamp = timestamp
    )
}

// Domain → Entity
fun Vehicle.toEntity(): VehicleEntity {
    return VehicleEntity(
        id = id,
        vehicleNo = vehicleNo,
        vehicleType = vehicleType,
        timestamp = timestamp
    )
}