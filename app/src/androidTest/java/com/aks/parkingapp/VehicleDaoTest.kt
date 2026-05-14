package com.aks.parkingapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aks.parkingapp.data.local.db.AppDatabase
import com.aks.parkingapp.data.local.dao.VehicleDao
import com.aks.parkingapp.data.local.entity.VehicleEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VehicleDaoTest {


    private lateinit var database: AppDatabase
    private lateinit var dao: VehicleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.vehicleDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertVehicles_andCheckLatestFirst() = runTest {
        val vehicle1 = VehicleEntity(0, 1111, 1, 1000L)
        val vehicle2 = VehicleEntity(0, 2222, 1, 2000L) // latest

        dao.insert(vehicle1)
        dao.insert(vehicle2)

        val result = dao.getAllVehicles().first()

        assertEquals(2, result.size)

        // Latest should be first
        assertEquals(2222, result[0].vehicleNo)
        assertEquals(1111, result[1].vehicleNo)

        // Delete the record
        dao.deleteVehicleById(result[0].id)

        dao.deleteVehicleById(999)

        assertEquals(2, result.size)
    }


}