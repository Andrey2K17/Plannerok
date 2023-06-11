package com.pg13.data.repositories

import com.pg13.data.mappers.mapToData
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.prefs.PrefDataSource
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.repositories.PrefDatSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefDataSourceRepositoryImpl(
    private val prefDataSource: PrefDataSource
): PrefDatSourceRepository {
    override fun getAuthData(): Flow<AuthDataDomain> {
        return prefDataSource.exampleCounterFlow.map { it.mapToDomain() }
    }

    override suspend fun setAuthData(authDataDomain: AuthDataDomain) {
        prefDataSource.setCounter(authDataDomain.mapToData())
    }
}