package com.sofiral.github_search_renew.presentation.search.model

data class RepoSearchUiModel(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
)