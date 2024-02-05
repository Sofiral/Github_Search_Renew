package com.sofiral.github_search_renew.presentation.search.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.sofiral.github_search_renew.domain.entity.RepoEntity
import com.sofiral.github_search_renew.domain.usecase.GetReposUseCase
import com.sofiral.github_search_renew.presentation.search.cache.SearchCache
import com.sofiral.github_search_renew.presentation.search.mapper.FromRepoEntityToSearchUiModel
import com.sofiral.github_search_renew.presentation.search.model.RepoSearchUiModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class GithubPagingSource(
    private val getReposUseCase: GetReposUseCase,
    private val query: String,
    private val mapper: FromRepoEntityToSearchUiModel,
    private val cache: SearchCache
) : RxPagingSource<Int, RepoSearchUiModel>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, RepoSearchUiModel>> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        val apiQuery = query

        return getReposUseCase.execute(apiQuery, position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { repos ->
                cache.reposList.addAll(repos)
                mapToLoadResult(repos, position, params)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun mapToLoadResult(
        reposList: List<RepoEntity>,
        position: Int,
        params: LoadParams<Int>
    ): LoadResult<Int, RepoSearchUiModel> {
        val mappedRepos = reposList.map(mapper::map)
        val nextKey = if (mappedRepos.isEmpty()) {
            null
        } else {
            position + (params.loadSize / NETWORK_PAGE_SIZE)
        }

        return LoadResult.Page(
            data = mappedRepos,
            prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, RepoSearchUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val GITHUB_STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 30
    }
}