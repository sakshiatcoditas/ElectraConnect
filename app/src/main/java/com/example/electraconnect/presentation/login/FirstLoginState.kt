package com.example.electraconnect.presentation.login


data class FirstLoginState(
    val email: String = "",
    val tempPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
