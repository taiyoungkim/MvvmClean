package com.example.mvvmclean.di

import com.example.data.remote.api.GithubApi
import com.example.data.remote.datasource.GithubDataSource
import com.example.data.remote.datasourceImpl.GithubDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceImplModule {

    @Provides
    @Singleton
    fun provideMainDataSource(
        githubApi: GithubApi
    ) : GithubDataSource {
        return GithubDataSourceImpl(
            githubApi
        )

    }
}