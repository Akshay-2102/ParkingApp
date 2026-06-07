package com.aks.parkingapp.di

import android.content.Context
import androidx.room.Room
import com.aks.parkingapp.data.local.dao.UserDao
import com.aks.parkingapp.data.local.db.AppDatabase
import com.aks.parkingapp.data.local.dao.VehicleDao
import com.aks.parkingapp.data.remote.ApiService
import com.aks.parkingapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).build()

    @Provides
    fun provideVehicleDao(db: AppDatabase): VehicleDao = db.vehicleDao()

    @Provides
    fun provideUserDao(
        db: AppDatabase
    ): UserDao {
        return db.userDao()
    }



    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}