package com.sofiral.github_search_renew.presentation

import android.content.Context
import com.github.terrakok.cicerone.Router
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.sofiral.github_search_renew.navigation.Screens
import com.sofiral.github_search_renew.presentation.core.Config
import com.sofiral.github_search_renew.presentation.core.Presenter
import com.sofiral.github_search_renew.presentation.delegate.GoogleSignDelegate
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router,
    private val googleSignDelegate: GoogleSignDelegate,
    private val context: Context,
    private val config: Config
) : Presenter<MainView>() {

    fun signOut() {
        GoogleSignIn.getLastSignedInAccount(context)?.let {
            googleSignDelegate.googleSignInClient.signOut().addOnCompleteListener {
                config.uniqueId = null
                router.navigateTo(Screens.Main())
            }
        } ?: run {
            config.uniqueId = null
            router.navigateTo(Screens.Main())
        }
    }

    fun openStartFragment() {
        if (config.isAuthorized) {
            router.newRootScreen(Screens.Home())
        } else {
            router.newRootScreen(Screens.Main())
        }
    }
}