package com.pg13.domain.entities

data class AuthDataDomain(
    val refreshToken: String? = "", val accessToken: String? = "",
)