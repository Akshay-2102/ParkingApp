package com.aks.parkingapp.domain.model.login

data class LoginResultModel(
    val accessToken: String,
    val refreshToken: String,
    val responseCode: String,
    val responseMessage: String,
    val success: Boolean
)