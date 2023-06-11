package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class RegisterDataBody(
    @SerializedName("phone") val phone: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String
)
