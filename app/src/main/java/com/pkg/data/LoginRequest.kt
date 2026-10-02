package com.pkg.data

data class LoginRequest(

    val username: String,
    val password: String,
    val expiresInMins : Int
)
