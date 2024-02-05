package com.sofiral.github_search_renew.di

import com.sofiral.github_search_renew.data.network.api.ApiProvider
import com.sofiral.github_search_renew.data.network.api.GithubApi
import toothpick.config.Module

class NetworkModule: Module() {
    init {
        bind(GithubApi::class.java).toProvider(ApiProvider::class.java).providesSingleton()
    }
}