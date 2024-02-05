package com.sofiral.github_search_renew.di

import com.sofiral.github_search_renew.data.room.AppDatabase
import com.sofiral.github_search_renew.data.room.DatabaseProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind

class DBModule : Module() {
    init {
        bind(AppDatabase::class.java).toProvider(DatabaseProvider::class.java).singleton()
    }
}