package com.sofiral.github_search_renew.data.network.api

import com.sofiral.github_search_renew.data.network.response.RepoSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Single<RepoSearchResponse>
}