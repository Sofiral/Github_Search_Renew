package com.sofiral.github_search_renew.presentation.detail

import com.sofiral.github_search_renew.presentation.core.Presentable
import com.sofiral.github_search_renew.presentation.detail.model.DetailRepoUiModel
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface DetailView : Presentable {

    fun showDetailRepo(repo: DetailRepoUiModel, isUserAuthorized: Boolean)

    @OneExecution
    fun showSuccessResult(message: String)
}