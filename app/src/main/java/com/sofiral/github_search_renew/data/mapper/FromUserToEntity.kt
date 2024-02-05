package com.sofiral.github_search_renew.data.mapper

import com.sofiral.github_search_renew.data.room.model.User
import com.sofiral.github_search_renew.domain.entity.UserEntity
import javax.inject.Inject

class FromUserToEntity @Inject constructor() {
    fun map(user: User) = UserEntity(
        id = user.uniqueId,
        email = user.email
    )
}