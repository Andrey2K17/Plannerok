package com.pg13.domain.repositories

import com.pg13.domain.entities.CheckAuthCode
import com.pg13.domain.entities.IsSuccess
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun sendAuthCode(phone: String): Flow<Resource<IsSuccess>>

    fun checkAuthCode(phone: String, code: String): Flow<Resource<CheckAuthCode>>
}