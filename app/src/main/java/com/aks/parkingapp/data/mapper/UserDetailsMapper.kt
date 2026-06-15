package com.aks.parkingapp.data.mapper

import com.aks.parkingapp.data.remote.userDetailsDTO.UserDetailsResponseDTO
import com.aks.parkingapp.domain.model.userDetails.UserDetailsModel

fun UserDetailsResponseDTO.toUserDetailsResult(): UserDetailsModel {
    return UserDetailsModel(
        id = user.id,
        email = user.email,
        name = user.name
    )
}