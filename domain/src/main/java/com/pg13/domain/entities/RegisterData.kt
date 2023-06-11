package com.pg13.domain.entities

data class RegisterData(
    val refreshToken: String,
    val accessToken: String,
    val userId: Int
)