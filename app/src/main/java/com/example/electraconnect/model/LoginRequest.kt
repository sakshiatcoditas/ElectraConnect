package com.example.electraconnect.model

data class LoginRequest (

    val email: String,
    val password: String,
    val remember_me: Boolean = true

)