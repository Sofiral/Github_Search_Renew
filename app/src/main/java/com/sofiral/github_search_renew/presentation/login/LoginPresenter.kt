package com.sofiral.github_search_renew.presentation.login

import com.github.terrakok.cicerone.Router
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.domain.usecase.AddUserUseCase
import com.sofiral.github_search_renew.domain.usecase.GetUserUseCase
import com.sofiral.github_search_renew.navigation.Screens.Home
import com.sofiral.github_search_renew.presentation.core.Config
import com.sofiral.github_search_renew.presentation.core.Presenter
import com.sofiral.github_search_renew.util.ResourceProvider
import com.sofiral.github_search_renew.util.async
import moxy.InjectViewState
import java.util.UUID
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(
    private val router: Router,
    private val getUserUseCase: GetUserUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val resourceProvider: ResourceProvider,
    private val config: Config
) : Presenter<LoginView>() {

    fun signInGoogle() {
        viewState.signIn()
    }

    fun signInUnauthorized() {
        saveId(id = null)
        navigateToHome()
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            getUser(account)
        } catch (e: ApiException) {
            viewState.showError(e.status.statusMessage ?: resourceProvider.getString(R.string.standard_error_msg))
        }
    }

    private fun getUser(account: GoogleSignInAccount) {
        getUserUseCase.execute(email = account.email.orEmpty())
            .async()
            .subscribe(
                { user -> dispatchUserData(user.id) },
                { e -> viewState.showError(e.message.toString()) },
                { addUserInDb(account) }
            )
            .addFullLifeCycle()
    }

    private fun addUserInDb(account: GoogleSignInAccount) {
        val uniqueId = UUID.randomUUID().toString()

        addUserUseCase.execute(uniqueId, account.email.orEmpty())
            .async()
            .subscribe(
                { dispatchUserData(uniqueId) },
                { e -> viewState.showError(e.message.toString()) }
            )
            .addFullLifeCycle()
    }

    private fun dispatchUserData(id: String) {
        saveId(id)
        navigateToHome()
    }

    private fun saveId(id: String?) {
        config.uniqueId = id
    }

    private fun navigateToHome() {
        router.navigateTo(Home())
    }
}