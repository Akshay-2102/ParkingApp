package com.aks.parkingapp.domain.model.register

data class RegisterResultModel(
    val id: Int?,
    val responseCode: String?,
    val responseMessage: String?,
    val success: Boolean?
)