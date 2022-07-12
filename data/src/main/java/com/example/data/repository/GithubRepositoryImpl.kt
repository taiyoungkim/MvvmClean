package com.example.data.repository

import com.example.data.mapper.Mapper
import com.example.data.remote.datasource.GithubDataSource
import com.example.domain.model.GithubResponse
import com.example.domain.repository.GithubRepository
import com.example.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubDataSource: GithubDataSource
) : GithubRepository {
    override suspend fun getGithub(
        remoteErrorEmitter: RemoteErrorEmitter,
        owner: String
    ): List<GithubResponse>? {
        return Mapper.mapperGithub(githubDataSource.getGithub(remoteErrorEmitter, owner))
    }
}