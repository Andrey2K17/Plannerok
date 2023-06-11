package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class IsSuccessRemote(
    @SerializedName("is_success") val isSuccess: Boolean
)
