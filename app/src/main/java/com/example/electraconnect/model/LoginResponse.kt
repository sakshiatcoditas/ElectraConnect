package com.example.electraconnect.model

data class LoginResponse(
val access_token: String?,
val refresh_token: String?,
val user: UserResponse?,
val need_password_reset: Boolean? = false

)
