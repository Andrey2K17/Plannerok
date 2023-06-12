package com.pg13.data.entities.remote

import com.google.gson.annotations.SerializedName

data class ProfileDataRemote(
    @SerializedName("profile_data") val profileData: ProfileRemote
)