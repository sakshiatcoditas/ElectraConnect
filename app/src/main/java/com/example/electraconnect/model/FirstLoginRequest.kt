package com.example.electraconnect.model

data class FirstLoginRequest(
    val email: String,
    val temp_password: String,
    val new_password: String,
    val confirm_password: String
)