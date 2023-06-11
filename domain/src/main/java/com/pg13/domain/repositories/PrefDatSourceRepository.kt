package com.pg13.domain.repositories

import com.pg13.domain.entities.AuthDataDomain
import kotlinx.coroutines.flow.Flow

interface PrefDatSourceRepository {
    fun getAuthData(): Flow<AuthDataDomain>
    suspend fun setAuthData(authDataDomain: AuthDataDomain)
}