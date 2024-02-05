package com.sofiral.github_search_renew.presentation.detail.mapper

import com.sofiral.github_search_renew.domain.entity.RepoEntity
import com.sofiral.github_search_renew.presentation.detail.model.DetailRepoUiModel
import com.sofiral.github_search_renew.presentation.detail.model.RepoOwnerSearchUiModel
import javax.inject.Inject

class FromRepoEntityToDetailUiModel @Inject constructor() {
    fun map(entity: RepoEntity): DetailRepoUiModel {
        return DetailRepoUiModel(
            name = entity.name,
            fullName = entity.fullName,
            description = entity.description.orEmpty(),
            language = entity.language.orEmpty(),
            stars = entity.stars.toString(),
            forks = entity.forks.toString(),
            creationDate = entity.creationDate,
            owner = RepoOwnerSearchUiModel(
                login = entity.owner.login,
                avatarUrl = entity.owner.avatarUrl
            )
        )
    }
}