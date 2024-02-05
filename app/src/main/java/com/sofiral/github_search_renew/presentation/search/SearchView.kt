package com.sofiral.github_search_renew.presentation.search

import androidx.paging.PagingData
import com.sofiral.github_search_renew.presentation.core.Presentable
import com.sofiral.github_search_renew.presentation.search.model.RepoSearchUiModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SearchView : Presentable {
    fun updateAdapter(data: PagingData<RepoSearchUiModel>)
    fun clearViews()
}