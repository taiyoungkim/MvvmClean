package com.example.data.remote.datasourceImpl

import com.example.data.remote.api.GithubApi
import com.example.data.remote.datasource.GithubDataSource
import com.example.data.utils.base.BaseRepository
import com.example.domain.model.GithubResponse
import com.example.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(
    private val githubApi: GithubApi
) : BaseRepository(), GithubDataSource {
    override suspend fun getGithub(remoteErrorEmitter: RemoteErrorEmitter, owner : String): List<GithubResponse>? {
        return safeApiCall(remoteErrorEmitter){githubApi.getRepos(owner).body()}
    }
}