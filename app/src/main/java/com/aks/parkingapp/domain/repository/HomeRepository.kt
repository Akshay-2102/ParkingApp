package com.aks.parkingapp.domain.repository


import com.aks.parkingapp.domain.model.userDetails.UserDetailsModel

interface HomeRepository {

    suspend fun getUserDetails(): Result<UserDetailsModel>

}