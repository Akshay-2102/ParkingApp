package com.aks.parkingapp.data.remote

import com.aks.parkingapp.data.remote.registerDTO.RegisterRequestDTO
import com.aks.parkingapp.data.remote.registerDTO.RegisterResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun registerUser(
        @Body request: RegisterRequestDTO
    ): RegisterResponseDTO


}