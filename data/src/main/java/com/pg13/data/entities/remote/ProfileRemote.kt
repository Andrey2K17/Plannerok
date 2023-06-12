package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName
import com.pg13.domain.entities.Avatars

data class ProfileRemote(
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("vk") val vk: String?,
    @SerializedName("instagram") val instagram: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("last") val last: String?,
    @SerializedName("online") val online: Boolean,
    @SerializedName("created") val created: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("completed_task") val completedTask: Int,
    @SerializedName("avatars") val avatars: Avatars?,
)