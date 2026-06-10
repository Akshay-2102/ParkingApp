package com.aks.parkingapp.data.mapper

import com.aks.parkingapp.data.remote.registerDTO.RegisterRequestDTO
import com.aks.parkingapp.data.remote.registerDTO.RegisterResponseDTO
import com.aks.parkingapp.domain.model.register.RegisterRequest
import com.aks.parkingapp.domain.model.register.RegisterResult


fun RegisterRequest.toRegisterRequestDTO(): RegisterRequestDTO {
    return RegisterRequestDTO(
        name = name,
        email = email,
        mobile = mobileNo,
        password = password,
    )
}


fun RegisterResponseDTO.toRegisterResult(): RegisterResult {

    return RegisterResult(
        id = id ?: 0,
        responseMessage = responseMessage ?: "",
        responseCode = responseCode ?: "",
        success = success ?: false
    )
}