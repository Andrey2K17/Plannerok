package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class CheckAuthCodeBody(
    @SerializedName("phone") val phone: String,
    @SerializedName("code") val code: String,
)
