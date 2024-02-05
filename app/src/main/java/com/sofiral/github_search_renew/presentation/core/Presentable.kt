package com.sofiral.github_search_renew.presentation.core

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface Presentable : MvpView {
    @OneExecution
    fun showError(message: String)
}