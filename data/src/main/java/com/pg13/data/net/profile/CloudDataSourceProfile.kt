package com.pg13.data.net.profile

import com.pg13.data.api.UserClient
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.util.networkBoundResource
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceProfile(
    private val api: UserClient
) {

    fun getCurrentProfile(): Flow<Resource<Profile>> = networkBoundResource(
        { api.getCurrentUser() },
        { cloudData -> cloudData.profileData.mapToDomain() }
    )
}