package com.pg13.plannerok.di

import com.pg13.data.api.UserClient
import com.pg13.data.net.auth_code.CloudDataSourceAuthCode
import com.pg13.data.net.auth_code.CloudDataSourceCheckAuthCode
import com.pg13.data.repositories.UserRepositoryImpl
import com.pg13.domain.repositories.UserRepository
import com.pg13.domain.usecases.CheckAuthCodeUseCase
import com.pg13.domain.usecases.SendAuthCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Singleton
    @Provides
    fun provideClient(retrofit: Retrofit): UserClient {
        return retrofit.create(UserClient::class.java)
    }

    @Singleton
    @Provides
    fun provideSendAuthCodeUseCase(repository: UserRepository): SendAuthCodeUseCase {
        return SendAuthCodeUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideCheckAuthCodeUseCase(repository: UserRepository): CheckAuthCodeUseCase {
        return CheckAuthCodeUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAuthCodeCloudDataSource(client: UserClient): CloudDataSourceAuthCode {
        return CloudDataSourceAuthCode(client)
    }

    @Singleton
    @Provides
    fun provideCheckAuthCodeCloudDataSource(client: UserClient): CloudDataSourceCheckAuthCode {
        return CloudDataSourceCheckAuthCode(client)
    }


    @Singleton
    @Provides
    fun provideUserRepository(
        cloudDataSourceAuthCode: CloudDataSourceAuthCode,
        cloudDataSourceCheckAuthCode: CloudDataSourceCheckAuthCode
    ): UserRepository {
        return UserRepositoryImpl(cloudDataSourceAuthCode, cloudDataSourceCheckAuthCode)
    }

}