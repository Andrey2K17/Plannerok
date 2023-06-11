package com.pg13.domain.repositories

import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun register(phone: String, name: String, userName: String): Flow<Resource<RegisterData>>
}