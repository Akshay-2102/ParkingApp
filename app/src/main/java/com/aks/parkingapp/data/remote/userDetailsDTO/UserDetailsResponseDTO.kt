package com.aks.parkingapp.data.remote.userDetailsDTO

data class UserDetailsResponseDTO(
    val responseCode: String,
    val responseMessage: String,
    val success: Boolean,
    val user: User
)