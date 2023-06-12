package com.pg13.plannerok.entities

import android.os.Parcelable
import com.pg13.plannerok.utils.getZodiacSign
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileUI(
    var name: String,
    var userName: String,
    var birthday: String,
    var city: String,
    var vk: String,
    var instagram: String,
    var status: String,
    var avatar: String,
    val id: Int,
    var last: String,
    var online: Boolean,
    var created: String,
    var phone: String,
    var completedTask: Int,
    var avatars: AvatarsUI,
    var zodiacSign: String = getZodiacSign(birthday.substring(8, 10).toInt(), birthday.substring(5, 7).toInt())
) : Parcelable