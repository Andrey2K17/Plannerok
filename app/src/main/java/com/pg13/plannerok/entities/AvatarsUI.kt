package com.pg13.plannerok.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AvatarsUI(
    val avatar: String = "",
    val bigAvatar: String = "",
    val miniAvatar: String = ""
) : Parcelable
