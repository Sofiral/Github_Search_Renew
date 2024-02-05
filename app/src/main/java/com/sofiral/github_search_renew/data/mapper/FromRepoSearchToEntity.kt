package com.sofiral.github_search_renew.data.mapper

import com.sofiral.github_search_renew.data.network.response.RepoSearchResponse
import com.sofiral.github_search_renew.domain.entity.RepoEntity
import com.sofiral.github_search_renew.domain.entity.RepoOwnerEntity
import com.sofiral.github_search_renew.util.TimeUtil
import javax.inject.Inject

class FromRepoSearchToEntity @Inject constructor(
    private val timeUtil: TimeUtil
) {
    fun map(response: RepoSearchResponse): List<RepoEntity> {
        return response.items.map { repo ->
            with(repo) {
                RepoEntity(
                    id = id,
                    name = name,
                    fullName = fullName,
                    owner = RepoOwnerEntity(
                        login = owner.login,
                        avatarUrl = owner.avatarUrl
                    ),
                    description = description,
                    language = language,
                    stars = stars,
                    forks = forks,
                    creationDate = timeUtil.getDateFromFormat(time = creationDate)
                )
            }
        }
    }
}