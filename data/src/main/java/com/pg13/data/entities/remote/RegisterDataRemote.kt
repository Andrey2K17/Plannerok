package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class RegisterDataRemote(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("user_id") val userId: Int
)