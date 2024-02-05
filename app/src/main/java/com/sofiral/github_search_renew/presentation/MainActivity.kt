package com.sofiral.github_search_renew.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.di.DBModule
import com.sofiral.github_search_renew.di.GoogleSignModule
import com.sofiral.github_search_renew.di.NetworkModule
import com.sofiral.github_search_renew.presentation.core.BaseActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class MainActivity : BaseActivity(), MainView {

    override val navigator = AppNavigator(this, R.id.fragmentContainer)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter =
        Toothpick.openScopes(Scopes.ACTIVITY_SCOPE, scopeName)
            .getInstance(MainPresenter::class.java)
            .also { Toothpick.closeScope(scopeName) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            presenter.openStartFragment()
        }

    }

    override fun injectDependencies() {
        Toothpick.openScopes(Scopes.APP_SCOPE_ROOT, Scopes.ACTIVITY_SCOPE)
            .installModules(
                DBModule(),
                NetworkModule(),
                GoogleSignModule()
            ).inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                presenter.signOut()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}