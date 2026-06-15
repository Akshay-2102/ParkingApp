package com.aks.parkingapp.data.remote

import com.aks.parkingapp.data.remote.loginDTO.LoginRequestDTO
import com.aks.parkingapp.data.remote.loginDTO.LoginResponseDTO
import com.aks.parkingapp.data.remote.registerDTO.RegisterRequestDTO
import com.aks.parkingapp.data.remote.registerDTO.RegisterResponseDTO
import com.aks.parkingapp.data.remote.userDetailsDTO.UserDetailsResponseDTO
import com.aks.parkingapp.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.REGISTER)
    suspend fun registerUser(
        @Body request: RegisterRequestDTO
    ): RegisterResponseDTO


    @POST(Constants.LOGIN)
    suspend fun loginUser(
        @Body request: LoginRequestDTO
    ): LoginResponseDTO

    @GET(Constants.GET_USER_DETAILS)
    suspend fun getUserDetails(
        @Header("Authorization") token: String
    ): UserDetailsResponseDTO

}