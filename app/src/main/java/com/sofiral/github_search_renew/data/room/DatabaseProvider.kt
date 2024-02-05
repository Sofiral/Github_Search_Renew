package com.sofiral.github_search_renew.data.room

import android.content.Context
import androidx.room.Room
import javax.inject.Inject
import javax.inject.Provider

class DatabaseProvider @Inject constructor(
    private val context: Context
) : Provider<AppDatabase> {

    override fun get(): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    companion object {
        private const val DATABASE_NAME = "app_db"
    }
}