package com.sofiral.github_search_renew.data.repository

import com.sofiral.github_search_renew.data.mapper.FromRepoSearchToEntity
import com.sofiral.github_search_renew.data.network.api.GithubApi
import com.sofiral.github_search_renew.domain.entity.RepoEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val mapper: FromRepoSearchToEntity
) {

    fun getSearchResultStream(query: String, position: Int, page: Int): Single<List<RepoEntity>> {
        return githubApi.searchRepos(query, position, page)
            .map(mapper::map)
    }
}