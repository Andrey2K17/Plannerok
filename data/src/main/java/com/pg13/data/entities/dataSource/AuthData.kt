package com.pg13.data.entities.dataSource

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthData(
    @SerializedName("refresh_token") val refreshToken: String? = "",
    @SerializedName("access_token") val accessToken: String? = ""
)