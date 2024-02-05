package com.sofiral.github_search_renew.domain.entity

class BookmarkEntity(
    val id: Long,
    val name: String,
    val description: String?,
    val login: String,
    val avatarUrl: String,
    val stars: Int,
    val forks: Int,
    val creationDate: String,
    val userId: String
)