package com.sofiral.github_search_renew.domain.usecase

import com.sofiral.github_search_renew.data.repository.UserRepository
import com.sofiral.github_search_renew.data.room.model.User
import com.sofiral.github_search_renew.domain.entity.UserEntity
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    fun execute(email: String): Maybe<UserEntity> =
        repository.getUserByEmail(email)

}