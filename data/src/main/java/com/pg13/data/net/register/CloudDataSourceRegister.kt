package com.pg13.data.net.register

import com.pg13.data.api.UserClient
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.util.networkBoundResource
import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceRegister(
    private val api: UserClient
) {

    fun register(phone: String, name: String, username: String): Flow<Resource<RegisterData>> =
        networkBoundResource(
            { api.register(phone, name, username) },
            { cloudData -> cloudData.mapToDomain() }
        )
}