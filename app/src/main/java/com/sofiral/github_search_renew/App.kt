package com.sofiral.github_search_renew

import android.app.Application
import com.github.stephanenicolas.toothpick.smoothie.BuildConfig
import com.sofiral.github_search_renew.di.AppModule
import com.sofiral.github_search_renew.di.Scopes.APP_SCOPE_ROOT
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initApp()
    }

    private fun initApp() {
        setupTimber()
        setupToothpick()
        setupBaseScope()
    }

    private fun setupToothpick() {
        val configuration = if (BuildConfig.DEBUG) {
            Configuration.forDevelopment().preventMultipleRootScopes()
        } else {
            Configuration.forProduction()
        }

        Toothpick.setConfiguration(configuration)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupBaseScope() {
        Toothpick.openScope(APP_SCOPE_ROOT)
            .installModules(
                AppModule(this),
            )
    }
}