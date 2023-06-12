package com.pg13.plannerok.di

import com.pg13.data.api.UserClient
import com.pg13.data.net.profile.CloudDataSourceProfile
import com.pg13.data.repositories.ProfileRepositoryImpl
import com.pg13.domain.repositories.ProfileRepository
import com.pg13.domain.usecases.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {

    @Singleton
    @Provides
    fun provideProfileCloudDataSource(client: UserClient): CloudDataSourceProfile {
        return CloudDataSourceProfile(client)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(cloudDataSourceProfile: CloudDataSourceProfile): ProfileRepository {
        return ProfileRepositoryImpl(cloudDataSourceProfile)
    }

    @Singleton
    @Provides
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase((profileRepository))
    }
}