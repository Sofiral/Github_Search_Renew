package com.sofiral.github_search_renew.domain.entity

class RepoEntity(
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: RepoOwnerEntity,
    val description: String?,
    val language: String?,
    val stars: Int,
    val forks: Int,
    val creationDate: String
)

class RepoOwnerEntity(
    val login: String,
    val avatarUrl: String,
)
