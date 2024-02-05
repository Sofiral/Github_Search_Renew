package com.sofiral.github_search_renew.domain.usecase

import com.sofiral.github_search_renew.data.repository.BookmarkRepository
import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteBookmarkUseCse @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    fun execute(bookmarks: List<BookmarkEntity>): Completable =
        bookmarkRepository.deleteBookmarks(bookmarks)
}