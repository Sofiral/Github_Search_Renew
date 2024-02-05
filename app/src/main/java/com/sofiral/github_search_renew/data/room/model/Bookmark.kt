package com.sofiral.github_search_renew.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String?,
    val login: String,
    val avatarUrl: String,
    val stars: Int,
    val forks: Int,
    val creationDate: String,
    val userId: String
)

