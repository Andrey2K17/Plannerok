package com.pg13.data.net.profile

import com.pg13.data.api.UserClient
import com.pg13.data.entities.remote.UpdateUserBody
import com.pg13.data.util.networkBoundResource
import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceEditProfile(
    private val api: UserClient
) {
    fun editProfile(profile: UpdateUserBody): Flow<Resource<Avatars>> = networkBoundResource(
        { api.updateUser(profile) },
        { it }
    )
}