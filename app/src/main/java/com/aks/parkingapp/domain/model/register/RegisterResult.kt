package com.aks.parkingapp.domain.model.register

data class RegisterResult(
    val id: Int?,
    val responseCode: String?,
    val responseMessage: String?,
    val success: Boolean?
)