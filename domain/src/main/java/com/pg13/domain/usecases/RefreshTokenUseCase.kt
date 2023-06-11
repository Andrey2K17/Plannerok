package com.pg13.domain.usecases

import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.RefreshTokenRepository
import kotlinx.coroutines.flow.Flow

class RefreshTokenUseCase(private val repository: RefreshTokenRepository) {
    operator fun invoke(refreshToken: String): Flow<Resource<RegisterData>> =
        repository.refreshToken(refreshToken)
}