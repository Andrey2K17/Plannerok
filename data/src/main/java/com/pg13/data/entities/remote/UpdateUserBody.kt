package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class UpdateUserBody(
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("vk") val vk: String?,
    @SerializedName("instagram") val instagram: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("avatar") val avatar: AvatarBody?,
)