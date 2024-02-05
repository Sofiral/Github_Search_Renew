package com.sofiral.github_search_renew.data.network.response

import com.google.gson.annotations.SerializedName
import com.sofiral.github_search_renew.data.network.response.RepoDetailsResponse

class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    val items: List<RepoDetailsResponse> = emptyList(),
    val nextPage: Int? = null
)
