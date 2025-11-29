package com.example.electraconnect.repository

import com.example.electraconnect.model.FirstLoginRequest
import com.example.electraconnect.model.FirstLoginResponse
import com.example.electraconnect.model.LoginRequest
import com.example.electraconnect.model.LoginResponse
import com.example.electraconnect.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: ApiService
)
{
    suspend fun login(username:String,password:String): LoginResponse{
        val request= LoginRequest(username,password)
        return api.login(request)


    }


    suspend fun firstLoginSetPassword(
    email: String,
    temp: String,
    newPass: String,
    confirm: String
): FirstLoginResponse {
    val request = FirstLoginRequest(email, temp, newPass, confirm)
    return api.firstLoginSetPassword(request)
}


}