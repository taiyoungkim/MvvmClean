package com.example.domain.usecase

import com.example.domain.repository.GithubRepository
import com.example.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GetUserRepoUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun execute(remoteErrorEmitter: RemoteErrorEmitter, owner : String) = githubRepository.getGithub(remoteErrorEmitter, owner)
}