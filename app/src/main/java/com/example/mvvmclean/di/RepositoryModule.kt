package com.example.mvvmclean.di

import com.example.data.remote.datasourceImpl.GithubDataSourceImpl
import com.example.data.repository.GithubRepositoryImpl
import com.example.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        githubDataSourceImpl: GithubDataSourceImpl
    ): GithubRepository {
        return GithubRepositoryImpl(
            githubDataSourceImpl
        )
    }
}