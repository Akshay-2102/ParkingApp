package com.aks.parkingapp.data.mapper

import com.aks.parkingapp.data.remote.loginDTO.LoginRequestDTO
import com.aks.parkingapp.data.remote.loginDTO.LoginResponseDTO
import com.aks.parkingapp.domain.model.login.LoginRequestModel
import com.aks.parkingapp.domain.model.login.LoginResultModel

fun LoginRequestModel.toLoginRequestDTO(): LoginRequestDTO {
    return LoginRequestDTO(
        email = email,
        password = password,
    )
}


fun LoginResponseDTO.toLoginResult(): LoginResultModel {
    return LoginResultModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
        responseMessage = responseMessage,
        responseCode = responseCode,
        success = success
    )
}
