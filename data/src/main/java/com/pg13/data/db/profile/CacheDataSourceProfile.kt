package com.pg13.data.db.profile

import com.pg13.data.db.ProfileDao
import com.pg13.data.mappers.mapToDomain
import com.pg13.data.mappers.mapToLocal
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CacheDataSourceProfile(
    private val profileDao: ProfileDao
) {

    fun getProfile(): Flow<Resource<Profile?>> = flow {
        val profile = profileDao.getProfile().map { it.mapToDomain() }.firstOrNull()

        profile?.let { list ->
            if (list.isNotEmpty()) {
                emit(Resource.Loading())

                emit(Resource.Success(list[0]))
            } else emit(Resource.Success(null))
        } ?: emit(Resource.Success(null))
    }


    suspend fun saveProfile(profile: Profile) {
        profileDao.saveProfile(profile.mapToLocal())
    }

    suspend fun updateProfile(profile: Profile) {
        profileDao.updateProfile(profile.mapToLocal())
    }
}