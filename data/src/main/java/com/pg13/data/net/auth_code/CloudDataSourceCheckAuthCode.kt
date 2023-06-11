package com.pg13.data.net.auth_code

import com.pg13.data.api.UserClient
import com.pg13.data.entities.remote.CheckAuthCodeBody
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.util.networkBoundResource
import com.pg13.domain.entities.CheckAuthCode
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceCheckAuthCode(
    private val api: UserClient
) {

    fun checkAuthCode(phone: String, code: String): Flow<Resource<CheckAuthCode>> =
        networkBoundResource(
            { api.checkAuthCode(CheckAuthCodeBody(phone, code)) },
            { cloudData -> cloudData.mapToDomain() }
        )
}