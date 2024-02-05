package com.sofiral.github_search_renew.di

import com.sofiral.github_search_renew.presentation.delegate.GoogleSignDelegate
import toothpick.config.Module

class GoogleSignModule : Module() {
    init {
        bind(GoogleSignDelegate::class.java).singleton()
    }
}