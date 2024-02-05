package com.sofiral.github_search_renew.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sofiral.github_search_renew.data.room.dao.BookmarkDao
import com.sofiral.github_search_renew.data.room.dao.UserDao
import com.sofiral.github_search_renew.data.room.model.Bookmark
import com.sofiral.github_search_renew.data.room.model.User


@Database(entities = [User::class, Bookmark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookmarkDao(): BookmarkDao
}