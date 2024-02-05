package com.sofiral.github_search_renew.data.repository

import com.sofiral.github_search_renew.data.mapper.FromUserToEntity
import com.sofiral.github_search_renew.data.room.model.User
import com.sofiral.github_search_renew.data.room.AppDatabase
import com.sofiral.github_search_renew.domain.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localSource: AppDatabase,
    private val mapper: FromUserToEntity
) {

    fun getUserByEmail(email: String): Maybe<UserEntity> {
        return localSource.userDao()
            .getUserByEmail(email)
            .map(mapper::map)
    }

    fun addUser(id: String, email: String): Completable =
        localSource.userDao()
            .insertUser(User(uniqueId = id, email = email))
}