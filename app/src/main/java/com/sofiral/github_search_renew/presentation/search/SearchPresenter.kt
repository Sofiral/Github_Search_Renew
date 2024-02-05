package com.sofiral.github_search_renew.presentation.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.observable
import com.github.terrakok.cicerone.Router
import com.sofiral.github_search_renew.domain.usecase.GetReposUseCase
import com.sofiral.github_search_renew.navigation.Screens
import com.sofiral.github_search_renew.presentation.core.Presenter
import com.sofiral.github_search_renew.presentation.detail.model.DetailFragmentArgs
import com.sofiral.github_search_renew.presentation.search.cache.SearchCache
import com.sofiral.github_search_renew.presentation.search.mapper.FromRepoEntityToSearchUiModel
import com.sofiral.github_search_renew.presentation.search.model.RepoSearchUiModel
import com.sofiral.github_search_renew.presentation.search.paging.GithubPagingSource
import com.sofiral.github_search_renew.presentation.search.paging.GithubPagingSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.MainScope
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val getReposUseCase: GetReposUseCase,
    private val mapper: FromRepoEntityToSearchUiModel,
    private val cache: SearchCache,
    private val router: Router
) : Presenter<SearchView>() {

    private var pagingSource: PagingSource<Int, RepoSearchUiModel>? = null
    private val pagingDataScope = MainScope()

    private var query: String = ""

    fun onQuerySubmit(query: String) {
        this.query = query
        pagingSource?.invalidate() ?: initAdapter()
    }

    fun onQueryChanged(query: String) {
        if (query.isEmpty()) {
            viewState.clearViews()
        }
    }

    fun onRepoClicked(id: Long) {
        val selectedRepo = cache.reposList.find { it.id == id } ?: return
        router.navigateTo(Screens.Detail(DetailFragmentArgs(selectedRepo)))
    }

    private fun initAdapter() {
        val config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        Pager(config) {
            GithubPagingSource(
                getReposUseCase = getReposUseCase,
                mapper = mapper,
                query = query,
                cache = cache
            ).also { pagingSource = it }
        }.observable
            .cachedIn(pagingDataScope)
            .subscribe(viewState::updateAdapter) {}
            .addFullLifeCycle()
    }
}