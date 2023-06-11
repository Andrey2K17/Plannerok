package com.pg13.plannerok.di

import com.pg13.data.api.UserClient
import com.pg13.data.net.register.CloudDataSourceRegister
import com.pg13.data.repositories.RegisterRepositoryImpl
import com.pg13.domain.repositories.RegisterRepository
import com.pg13.domain.usecases.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RegisterModule {

    @Singleton
    @Provides
    fun provideRegisterUseCase(repository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideRegisterCloudDataSource(client: UserClient): CloudDataSourceRegister {
        return CloudDataSourceRegister(client)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(cloudDataSource: CloudDataSourceRegister): RegisterRepository {
        return RegisterRepositoryImpl(cloudDataSource)
    }

}