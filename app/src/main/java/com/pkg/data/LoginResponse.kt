package com.pkg.data
/**
 * Data class representing the response received upon successful user authentication.
 */
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
