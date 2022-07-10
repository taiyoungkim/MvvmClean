package com.example.domain.repository

import com.example.domain.model.GithubResponse
import com.example.domain.utils.RemoteErrorEmitter

interface GithubRepository {
    suspend fun getGithub(remoteErrorEmitter: RemoteErrorEmitter, owner : String) : List<GithubResponse>?
}