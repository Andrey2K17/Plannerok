package com.pg13.data.repositories

import com.pg13.data.api.UserClient
import com.pg13.data.entities.remote.RefreshTokenBody
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.util.networkBoundResource
import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.RefreshTokenRepository
import kotlinx.coroutines.flow.Flow

class RefreshTokenRepositoryImpl(
    private val api: UserClient
) : RefreshTokenRepository {
    override fun refreshToken(refreshToken: String): Flow<Resource<RegisterData>> =
        networkBoundResource(
            { api.refreshToken(RefreshTokenBody(refreshToken)) },
            { cloudData -> cloudData.mapToDomain() }
        )
}