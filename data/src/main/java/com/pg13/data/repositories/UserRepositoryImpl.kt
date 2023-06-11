package com.pg13.data.repositories

import com.pg13.data.net.auth_code.CloudDataSourceAuthCode
import com.pg13.data.net.auth_code.CloudDataSourceCheckAuthCode
import com.pg13.domain.entities.CheckAuthCode
import com.pg13.domain.entities.IsSuccess
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val cloudDataSourceAuthCode: CloudDataSourceAuthCode,
    private val cloudDataSourceCheckAuthCode: CloudDataSourceCheckAuthCode
) : UserRepository {

    override fun sendAuthCode(phone: String): Flow<Resource<IsSuccess>> {
        return cloudDataSourceAuthCode.sendAuthCode(phone)
    }

    override fun checkAuthCode(phone: String, code: String): Flow<Resource<CheckAuthCode>> {
        return cloudDataSourceCheckAuthCode.checkAuthCode(phone, code)
    }
}