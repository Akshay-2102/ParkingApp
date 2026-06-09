package com.aks.parkingapp.data.remote.registerDTO

data class RegisterRequestDTO(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)