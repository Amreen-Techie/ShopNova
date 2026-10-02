package com.pkg.data

data class LoginResponse(

    val token: String,
    val id: Int,
    val username: String,
    val accessToken: String,
    val refreshToken: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String
)
