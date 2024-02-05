package com.sofiral.github_search_renew.data.mapper

import com.sofiral.github_search_renew.data.room.model.Bookmark
import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import javax.inject.Inject

class BookmarkMapper @Inject constructor() {

    fun mapToBookmark(entity: BookmarkEntity): Bookmark {
        return Bookmark(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            login = entity.login,
            avatarUrl = entity.avatarUrl,
            stars = entity.stars,
            forks = entity.forks,
            creationDate = entity.creationDate,
            userId = entity.userId
        )
    }

    fun mapToEntity(bookmark: Bookmark): BookmarkEntity {
        return BookmarkEntity(
            id = bookmark.id,
            name = bookmark.name,
            description = bookmark.description,
            login = bookmark.login,
            avatarUrl = bookmark.avatarUrl,
            stars = bookmark.stars,
            forks = bookmark.forks,
            creationDate = bookmark.creationDate,
            userId = bookmark.userId
        )
    }
}