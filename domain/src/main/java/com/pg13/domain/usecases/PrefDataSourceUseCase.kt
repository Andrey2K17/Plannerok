package com.pg13.domain.usecases

import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.repositories.PrefDatSourceRepository
import kotlinx.coroutines.flow.Flow

class PrefDataSourceUseCase(private val repository: PrefDatSourceRepository) {

    fun getAuthData(): Flow<AuthDataDomain> = repository.getAuthData()

    suspend fun setAuthData(authDataDomain: AuthDataDomain) = repository.setAuthData(authDataDomain)
}