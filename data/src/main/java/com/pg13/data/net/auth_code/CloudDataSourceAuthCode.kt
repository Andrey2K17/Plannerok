package com.pg13.data.net.auth_code

import com.pg13.data.api.UserClient
import com.pg13.data.entities.remote.PhoneBody
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.util.networkBoundResource
import com.pg13.domain.entities.IsSuccess
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceAuthCode(
    private val api: UserClient
) {

    fun sendAuthCode(phone: String): Flow<Resource<IsSuccess>> = networkBoundResource(
        { api.sendAuthCode(PhoneBody(phone)) },
        { cloudData -> cloudData.mapToDomain() }
    )
}