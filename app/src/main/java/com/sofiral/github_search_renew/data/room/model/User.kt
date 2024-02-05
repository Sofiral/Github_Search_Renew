package com.sofiral.github_search_renew.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uniqueId: String,
    val email: String
)
