package com.pkg.data


/**
 * Data class representing the authentication login request payload.
 */
data class LoginRequest(

    val username: String,
    val password: String,
    val expiresInMins : Int
)
