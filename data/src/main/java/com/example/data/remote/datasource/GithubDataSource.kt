package com.example.data.remote.datasource

import com.example.domain.model.GithubResponse
import com.example.domain.utils.RemoteErrorEmitter

interface GithubDataSource {
    suspend fun getGithub(remoteErrorEmitter: RemoteErrorEmitter, owner: String) : List<GithubResponse>?
}