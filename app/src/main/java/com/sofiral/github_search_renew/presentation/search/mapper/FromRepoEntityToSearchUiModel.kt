package com.sofiral.github_search_renew.presentation.search.mapper

import com.sofiral.github_search_renew.domain.entity.RepoEntity
import com.sofiral.github_search_renew.presentation.search.model.RepoSearchUiModel
import javax.inject.Inject

class FromRepoEntityToSearchUiModel @Inject constructor() {
    fun map(entity: RepoEntity): RepoSearchUiModel {
        return RepoSearchUiModel(
            id = entity.id,
            name = entity.name,
            fullName = entity.fullName,
            description = entity.description.orEmpty(),
            language = entity.language.orEmpty()
        )
    }
}