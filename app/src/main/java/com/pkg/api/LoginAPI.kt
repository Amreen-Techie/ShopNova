package com.pkg.api

import com.pkg.data.LoginRequest
import com.pkg.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}