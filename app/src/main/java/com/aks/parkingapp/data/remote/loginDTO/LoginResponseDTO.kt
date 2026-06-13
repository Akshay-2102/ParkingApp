package com.aks.parkingapp.data.remote.loginDTO

data class LoginResponseDTO(
    val accessToken: String,
    val refreshToken: String,
    val responseCode: String,
    val responseMessage: String,
    val success: Boolean
)