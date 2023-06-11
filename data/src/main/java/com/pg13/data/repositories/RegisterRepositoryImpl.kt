package com.pg13.data.repositories

import com.pg13.data.net.register.CloudDataSourceRegister
import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow

class RegisterRepositoryImpl(
    private val cloudDataSourceRegister: CloudDataSourceRegister
) : RegisterRepository {
    override fun register(
        phone: String,
        name: String,
        userName: String
    ): Flow<Resource<RegisterData>> {
        return cloudDataSourceRegister.register(phone, name, userName)
    }
}