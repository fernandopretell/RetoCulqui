package com.fulbiopretell.retoculqui.di

import com.fulbiopretell.retoculqui.data.Api
import com.fulbiopretell.retoculqui.data.UserRemoteDataSource
import com.fulbiopretell.retoculqui.data.repository.UserRepository
import com.fulbiopretell.retoculqui.domain.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(api: Api): UserRemoteDataSource {
        return UserRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(dataSource: UserRemoteDataSource): UserRepository {
        return UserRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repository: UserRepository): UsersUseCase {
        return UsersUseCase(repository)
    }
}





