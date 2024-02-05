package com.sofiral.github_search_renew.presentation.search.cache

import com.sofiral.github_search_renew.domain.entity.RepoEntity
import javax.inject.Inject

class SearchCache @Inject constructor() {
    val reposList: MutableList<RepoEntity> = mutableListOf()
}