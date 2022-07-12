package com.example.data.mapper

import com.example.domain.model.GithubResponse

object Mapper {
    fun mapperGithub(response: List<GithubResponse>?) : List<GithubResponse>? {
        return response?.toDomain()
    }

    fun List<GithubResponse>.toDomain() : List<GithubResponse> {
        return this.map {
            GithubResponse(
                it.name,
                it.id,
                it.date,
                it.url
            )
        }
    }
}