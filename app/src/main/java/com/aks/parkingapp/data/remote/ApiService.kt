package com.aks.parkingapp.data.remote

import com.aks.parkingapp.data.remote.registerDTO.RegisterRequestDTO
import com.aks.parkingapp.data.remote.registerDTO.RegisterResponseDTO
import com.aks.parkingapp.utils.Constants
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.REGISTER)
    suspend fun registerUser(
        @Body request: RegisterRequestDTO
    ): RegisterResponseDTO


}