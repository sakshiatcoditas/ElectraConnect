package com.example.electraconnect.network

import com.example.electraconnect.model.FirstLoginRequest
import com.example.electraconnect.model.FirstLoginResponse
import com.example.electraconnect.model.LoginRequest
import com.example.electraconnect.model.LoginResponse
import okhttp3.Request
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

   @POST("/auth/login")
   suspend fun login(
       @Body request: LoginRequest
   ): LoginResponse


   @POST("/auth/first-login-set-password")
   suspend fun firstLoginSetPassword(
       @Body request: FirstLoginRequest
   ): FirstLoginResponse



}