package com.sofiral.github_search_renew.presentation.bookmark

import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import com.sofiral.github_search_renew.domain.usecase.DeleteBookmarkUseCse
import com.sofiral.github_search_renew.domain.usecase.GetUserBookmarksUseCase
import com.sofiral.github_search_renew.presentation.bookmark.mapper.FromBookmarkEntityToUiModel
import com.sofiral.github_search_renew.presentation.bookmark.model.BookmarkUiModel
import com.sofiral.github_search_renew.presentation.core.Config
import com.sofiral.github_search_renew.presentation.core.Presenter
import com.sofiral.github_search_renew.util.ResourceProvider
import com.sofiral.github_search_renew.util.async
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BookmarkPresenter @Inject constructor(
    private val getUserBookmarksUseCase: GetUserBookmarksUseCase,
    private val deleteBookmarkUseCse: DeleteBookmarkUseCse,
    private val config: Config,
    private val resourceProvider: ResourceProvider,
    private val mapper: FromBookmarkEntityToUiModel,
) : Presenter<BookmarkView>() {

    private var bookmarksList = mutableListOf<BookmarkEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        fetchBookmarks()
    }

    private fun fetchBookmarks() {
        config.uniqueId?.let { userId ->
            getUserBookmarksUseCase.execute(userId)
                .async()
                .doOnSuccess { bookmarksList.addAll(it) }
                .map { it.map(mapper::map) }
                .subscribe(::dispatchBookmarks)
                { viewState.showError(resourceProvider.getString(R.string.standard_error_msg)) }
                .addFullLifeCycle()
        } ?: return
    }

    private fun dispatchBookmarks(bookmarks: List<BookmarkUiModel>) {
        if (bookmarks.isEmpty()) {
            viewState.showAbsenceBookmarks()
        } else {
            viewState.updateBookmarks(bookmarks)
        }
    }

    fun deleteBookmark(bookmarkId: Long) {
        val selectedBookmark = bookmarksList.find { it.id == bookmarkId } ?: return

        deleteBookmarkUseCse.execute(listOf(selectedBookmark))
            .async()
            .subscribe(
                { dispatchDeletionResult(bookmarkId) },
                { viewState.showError(resourceProvider.getString(R.string.standard_error_msg)) }
            )
            .addFullLifeCycle()

    }

    private fun dispatchDeletionResult(bookmarkId: Long) {
        bookmarksList.removeIf { it.id == bookmarkId }
        viewState.deleteSpecificBookmark(bookmarkId)

        if (bookmarksList.isEmpty()) {
            viewState.showAbsenceBookmarks()
        }
    }

    fun deleteAllBookmarks() {
        deleteBookmarkUseCse.execute(bookmarksList)
            .async()
            .subscribe(::dispatchAllDeletedBookmarks)
            { viewState.showError(resourceProvider.getString(R.string.standard_error_msg)) }
            .addFullLifeCycle()


    }

    private fun dispatchAllDeletedBookmarks() {
        viewState.updateBookmarks(emptyList())
        viewState.showAbsenceBookmarks()
    }
}