package com.example.electraconnect.model

data class FirstLoginResponse(
    val access_token: String,
    val refresh_token: String,
    val user: UserResponse
)