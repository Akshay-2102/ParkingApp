package com.aks.parkingapp.domain.model.register

data class RegisterRequest(
    val name: String,
    val email: String,
    val mobileNo: String,
    val password: String
)
