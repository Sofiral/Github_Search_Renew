package com.sofiral.github_search_renew.presentation.login

import com.sofiral.github_search_renew.presentation.core.Presentable
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView : Presentable {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun signIn()
}