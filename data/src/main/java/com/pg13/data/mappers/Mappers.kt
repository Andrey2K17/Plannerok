package com.pg13.data.mappers

import com.pg13.data.entities.dataSource.AuthData
import com.pg13.data.entities.local.ProfileLocal
import com.pg13.data.entities.remote.AvatarBody
import com.pg13.data.entities.remote.CheckAuthCodeRemote
import com.pg13.data.entities.remote.IsSuccessRemote
import com.pg13.data.entities.remote.ProfileRemote
import com.pg13.data.entities.remote.RegisterDataRemote
import com.pg13.data.entities.remote.UpdateUserBody
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.entities.AvatarBodyDomain
import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.CheckAuthCode
import com.pg13.domain.entities.IsSuccess
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.UpdateUserBodyDomain

fun IsSuccessRemote.mapToDomain(): IsSuccess = IsSuccess(isSuccess)

fun RegisterDataRemote.mapToDomain(): RegisterData = RegisterData(refreshToken, accessToken, userId)

fun CheckAuthCodeRemote.mapToDomain(): CheckAuthCode =
    CheckAuthCode(refreshToken, accessToken, userId, isUserExists)

fun AuthData.mapToDomain(): AuthDataDomain = AuthDataDomain(refreshToken, accessToken)

fun AuthDataDomain.mapToData(): AuthData = AuthData(refreshToken, accessToken)

fun ProfileRemote.mapToDomain(): Profile = Profile(
    name,
    userName,
    birthday ?: "",
    city ?: "",
    vk ?: "",
    instagram ?: "",
    status ?: "",
    avatar ?: "",
    id,
    last ?: "",
    online,
    created,
    phone,
    completedTask,
    avatars ?: Avatars()
)

fun ProfileLocal.mapToDomain(): Profile = Profile(
    name,
    username,
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
    Avatars()
)

fun List<ProfileLocal>.mapToDomain(): List<Profile> {
    return map { it.mapToDomain() }
}

fun Profile.mapToLocal(): ProfileLocal = ProfileLocal(
    id,
    name,
    userName,
    birthday,
    city,
    vk,
    instagram,
    status,
    avatar,
    last,
    online,
    created,
    phone,
    completedTask
)

fun UpdateUserBodyDomain.mapToData(): UpdateUserBody = UpdateUserBody(
    name, userName, birthday, city, vk, instagram, status, avatar?.mapToData()
)

fun AvatarBodyDomain.mapToData(): AvatarBody = AvatarBody(
    filename, base64
)