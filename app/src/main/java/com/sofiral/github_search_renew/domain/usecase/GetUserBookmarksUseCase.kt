package com.sofiral.github_search_renew.domain.usecase

import com.sofiral.github_search_renew.data.repository.BookmarkRepository
import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    fun execute(userId: String): Single<List<BookmarkEntity>> {
        return bookmarkRepository.getBookmarks(userId)
    }
}