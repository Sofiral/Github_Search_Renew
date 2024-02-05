package com.sofiral.github_search_renew.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sofiral.github_search_renew.data.room.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email= :email")
    fun getUserByEmail(email: String): Maybe<User>

    @Insert
    fun insertUser(user: User): Completable
}