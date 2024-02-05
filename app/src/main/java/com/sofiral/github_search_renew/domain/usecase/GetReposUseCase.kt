package com.sofiral.github_search_renew.domain.usecase

import com.sofiral.github_search_renew.data.repository.SearchRepository
import com.sofiral.github_search_renew.domain.entity.RepoEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    fun execute(query: String, position: Int, page: Int): Single<List<RepoEntity>> {
        return searchRepository.getSearchResultStream(query, position, page)
    }
}