package com.sofiral.github_search_renew.data.network.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor() : Provider<GithubApi> {
    override fun get(): GithubApi = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(GithubApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}