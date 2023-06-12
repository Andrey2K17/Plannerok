package com.pg13.plannerok.mappers

import com.pg13.domain.entities.AvatarBodyDomain
import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.UpdateUserBodyDomain
import com.pg13.plannerok.entities.AvatarsUI
import com.pg13.plannerok.entities.ProfileUI

fun Profile.mapToUI(): ProfileUI = ProfileUI(
    name,
    userName,
    birthday,
    city,
    vk,
    instagram,
    status,
    avatar,
    id,
    last,
    online,
    created,
    phone,
    completedTask,
    avatars.mapToUI()
)

fun Avatars.mapToUI(): AvatarsUI = AvatarsUI(avatar, bigAvatar, miniAvatar)


fun ProfileUI.mapToDomain(): UpdateUserBodyDomain = UpdateUserBodyDomain(
    name, userName, birthday, city, vk, instagram, status, AvatarBodyDomain("", "")
)
