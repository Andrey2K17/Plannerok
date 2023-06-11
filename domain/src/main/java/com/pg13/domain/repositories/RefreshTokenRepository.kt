package com.pg13.domain.repositories

import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

interface RefreshTokenRepository {
    fun refreshToken(refreshToken: String): Flow<Resource<RegisterData>>
}