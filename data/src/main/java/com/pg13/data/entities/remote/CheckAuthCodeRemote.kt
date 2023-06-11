package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class CheckAuthCodeRemote(
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("user_id") val userId: Int?,
    @SerializedName("is_user_exists") val isUserExists: Boolean,
)