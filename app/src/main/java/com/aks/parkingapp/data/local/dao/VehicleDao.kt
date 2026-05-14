package com.aks.parkingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aks.parkingapp.data.local.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM VehicleEntity ORDER BY timestamp DESC")
    fun getAllVehicles(): Flow<List<VehicleEntity>>

    @Insert
    suspend fun insert(vehicleEntity: VehicleEntity)

    @Query("DELETE FROM vehicleEntity WHERE id = :vehicleId")
    suspend fun deleteVehicleById(vehicleId: Int)

    @Query("UPDATE VehicleEntity SET vehicleNo = :vehicleNo, vehicleType = :vehicleType WHERE id = :vehicleId")
    suspend fun updateVehicleById(vehicleNo: String,vehicleType: Int, vehicleId: Int)

}