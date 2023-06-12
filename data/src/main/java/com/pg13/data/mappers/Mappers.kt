package com.pg13.data.mappers

import com.pg13.data.entities.dataSource.AuthData
import com.pg13.data.entities.remote.CheckAuthCodeRemote
import com.pg13.data.entities.remote.IsSuccessRemote
import com.pg13.data.entities.remote.ProfileRemote
import com.pg13.data.entities.remote.RegisterDataRemote
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.CheckAuthCode
import com.pg13.domain.entities.IsSuccess
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.RegisterData

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