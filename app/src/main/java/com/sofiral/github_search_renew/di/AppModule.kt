package com.sofiral.github_search_renew.di

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.sofiral.github_search_renew.presentation.core.Config
import com.sofiral.github_search_renew.util.ResourceProvider
import toothpick.config.Module

class AppModule(application: Application) : Module() {
    init {
        val cicerone = Cicerone.create(Router())
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())

        bind(Context::class.java).toInstance(application.applicationContext)
        bind(Config::class.java).singleton()
        bind(ResourceProvider::class.java).singleton()
    }
}