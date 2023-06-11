package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class RefreshTokenBody(
    @SerializedName("refresh_token") val refreshToken: String
)