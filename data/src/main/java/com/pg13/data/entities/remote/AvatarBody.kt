package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class AvatarBody(
    @SerializedName("filename") val filename: String,
    @SerializedName("base_64") val base64: String
)