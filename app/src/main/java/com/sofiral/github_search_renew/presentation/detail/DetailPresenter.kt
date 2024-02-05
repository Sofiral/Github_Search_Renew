package com.sofiral.github_search_renew.presentation.detail

import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.domain.usecase.DeleteBookmarkUseCse
import com.sofiral.github_search_renew.domain.usecase.InsertBookmarkUseCase
import com.sofiral.github_search_renew.presentation.core.Config
import com.sofiral.github_search_renew.presentation.core.Presenter
import com.sofiral.github_search_renew.presentation.detail.mapper.FromRepoEntityToBookmarkEntity
import com.sofiral.github_search_renew.presentation.detail.mapper.FromRepoEntityToDetailUiModel
import com.sofiral.github_search_renew.presentation.detail.model.DetailFragmentArgs
import com.sofiral.github_search_renew.util.ResourceProvider
import com.sofiral.github_search_renew.util.async
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailPresenter @Inject constructor(
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCse: DeleteBookmarkUseCse,
    private val repoMapper: FromRepoEntityToDetailUiModel,
    private val bookmarkMapper: FromRepoEntityToBookmarkEntity,
    private val config: Config,
    private val resourceProvider: ResourceProvider,
    args: DetailFragmentArgs,
) : Presenter<DetailView>() {

    private val repo = args.repo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showDetailRepo(repoMapper.map(repo), config.isAuthorized)
    }

    fun addBookmark() {
        insertBookmarkUseCase.execute(bookmarkMapper.map(repo))
            .async()
            .subscribe(
                { viewState.showSuccessResult(resourceProvider.getString(R.string.bookmark_added)) },
                { viewState.showError(resourceProvider.getString(R.string.standard_error_msg)) })
            .addFullLifeCycle()
    }

    fun deleteBookmark() {
        deleteBookmarkUseCse.execute(listOf(bookmarkMapper.map(repo)))
            .async()
            .subscribe(
                { viewState.showSuccessResult(resourceProvider.getString(R.string.bookmark_deleted)) },
                { viewState.showError(resourceProvider.getString(R.string.standard_error_msg)) })
            .addFullLifeCycle()
    }
}