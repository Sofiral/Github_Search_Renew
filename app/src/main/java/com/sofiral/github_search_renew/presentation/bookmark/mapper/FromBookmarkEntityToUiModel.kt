package com.sofiral.github_search_renew.presentation.bookmark.mapper

import com.sofiral.github_search_renew.domain.entity.BookmarkEntity
import com.sofiral.github_search_renew.presentation.bookmark.model.BookmarkUiModel
import javax.inject.Inject

class FromBookmarkEntityToUiModel @Inject constructor() {
    fun map(entity: BookmarkEntity): BookmarkUiModel {
        return BookmarkUiModel(
            id = entity.id,
            name = entity.name,
            description = entity.description.orEmpty(),
            login = entity.login,
            avatarUrl = entity.avatarUrl,
            stars = entity.stars.toString(),
            forks = entity.forks.toString(),
            creationDate = entity.creationDate,
        )
    }
}