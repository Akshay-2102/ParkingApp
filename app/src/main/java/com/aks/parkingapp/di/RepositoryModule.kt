package com.aks.parkingapp.di

import com.aks.parkingapp.data.repository.HomeRepositoryImpl
import com.aks.parkingapp.data.repository.LoginRepositoryImpl
import com.aks.parkingapp.data.repository.RegisterRepositoryImpl
import com.aks.parkingapp.data.repository.OtpRepositoryImpl
import com.aks.parkingapp.data.repository.VehicleRepositoryImpl
import com.aks.parkingapp.domain.repository.HomeRepository
import com.aks.parkingapp.domain.repository.LoginRepository
import com.aks.parkingapp.domain.repository.RegistrationRepository
import com.aks.parkingapp.domain.repository.OtpRepository
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
    abstract fun bindRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ): RegistrationRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindOtpRepository(
        otpRepositoryImpl: OtpRepositoryImpl
    ): OtpRepository
}