package com.sofiral.github_search_renew.data.repository

import com.sofiral.github_search_renew.data.mapper.BookmarkMapper
import com.sofiral.github_search_renew.data.room.AppDatabase
import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val localSource: AppDatabase,
    private val mapper: BookmarkMapper
) {

    fun getBookmarks(userId: String): Single<List<BookmarkEntity>> {
        return localSource.bookmarkDao()
            .getBookmarksOfUser(userId)
            .map { it.map(mapper::mapToEntity) }
    }

    fun deleteBookmarks(bookmarks: List<BookmarkEntity>): Completable {
        return localSource.bookmarkDao()
            .deleteBookmark(bookmarks.map(mapper::mapToBookmark))
    }

    fun insertBookmark(bookmarkEntity: BookmarkEntity): Completable {
        return localSource.bookmarkDao()
            .insertBookmark(mapper.mapToBookmark(bookmarkEntity))
    }
}