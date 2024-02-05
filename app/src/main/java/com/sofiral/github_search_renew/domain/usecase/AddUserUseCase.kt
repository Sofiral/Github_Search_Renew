package com.sofiral.github_search_renew.domain.usecase

import com.sofiral.github_search_renew.data.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    fun execute(id: String, email: String): Completable = repository.addUser(id, email)
}