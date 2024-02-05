package com.sofiral.github_search_renew.presentation.detail.model

class DetailRepoUiModel(
    val name: String,
    val fullName: String,
    val owner: RepoOwnerSearchUiModel,
    val description: String,
    val language: String,
    val stars: String,
    val forks: String,
    val creationDate: String
)

class RepoOwnerSearchUiModel(
    val login: String,
    val avatarUrl: String,
)