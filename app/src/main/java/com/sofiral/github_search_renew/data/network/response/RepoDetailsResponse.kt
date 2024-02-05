package com.sofiral.github_search_renew.data.network.response

import com.google.gson.annotations.SerializedName

class RepoDetailsResponse(
    val id: Long,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val description: String?,
    val owner: OwnerResponse,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks_count") val forks: Int,
    val language: String?,
    @SerializedName("created_at") val creationDate: String,
) {
    class OwnerResponse(
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String,
    )
}