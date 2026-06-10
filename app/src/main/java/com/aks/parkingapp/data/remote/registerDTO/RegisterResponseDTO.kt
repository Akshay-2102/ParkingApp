package com.aks.parkingapp.data.remote.registerDTO

data class RegisterResponseDTO(
    val id: Int?,
    val responseCode: String?,
    val responseMessage: String?,
    val success: Boolean?
)
