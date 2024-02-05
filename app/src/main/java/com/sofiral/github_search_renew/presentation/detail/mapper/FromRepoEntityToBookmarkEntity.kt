package com.sofiral.github_search_renew.presentation.detail.mapper

import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import com.sofiral.github_search_renew.domain.entity.RepoEntity
import com.sofiral.github_search_renew.presentation.core.Config
import javax.inject.Inject

class FromRepoEntityToBookmarkEntity @Inject constructor(
    private val config: Config
) {
    fun map(repo: RepoEntity): BookmarkEntity {
        return BookmarkEntity(
            id = repo.id,
            name = repo.name,
            description = repo.description,
            login = repo.owner.login,
            avatarUrl = repo.owner.avatarUrl,
            stars = repo.stars,
            forks = repo.forks,
            creationDate = repo.creationDate,
            userId = config.uniqueId.orEmpty()
        )
    }
}