package com.sofiral.github_search_renew.presentation.bookmark

import com.sofiral.github_search_renew.presentation.bookmark.model.BookmarkUiModel
import com.sofiral.github_search_renew.presentation.core.Presentable
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface BookmarkView : Presentable {

    fun updateBookmarks(data: List<BookmarkUiModel>)
    fun showAbsenceBookmarks()

    @OneExecution
    fun deleteSpecificBookmark(bookmarkId: Long)
}