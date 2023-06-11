package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class PhoneBody(
    @SerializedName("phone")
    val phone: String
)