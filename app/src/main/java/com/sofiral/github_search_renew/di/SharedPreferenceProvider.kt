package com.sofiral.github_search_renew.di

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Provider

class SharedPreferenceProvider @Inject constructor(
    private val context: Context,
    private val preferenceName: String
) : Provider<SharedPreferences> {
    override fun get(): SharedPreferences {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }
}