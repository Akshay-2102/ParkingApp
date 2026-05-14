package com.aks.parkingapp.di

import com.aks.parkingapp.data.repository.AuthRepositoryImpl
import com.aks.parkingapp.data.repository.VehicleRepositoryImpl
import com.aks.parkingapp.domain.repository.AuthRepository
import com.aks.parkingapp.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}