package com.pg13.domain.entities

data class CheckAuthCode(
    val refreshToken: String?,
    val accessToken: String?,
    val userId: Int?,
    val isUserExists: Boolean,
)