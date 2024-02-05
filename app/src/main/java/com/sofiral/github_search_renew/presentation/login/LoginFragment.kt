package com.sofiral.github_search_renew.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.databinding.FragmentLoginBinding
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.presentation.core.CoreFragment
import com.sofiral.github_search_renew.presentation.delegate.GoogleSignDelegate
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import toothpick.ktp.delegate.inject


class LoginFragment : CoreFragment(), LoginView {

    override val layoutRes: Int = R.layout.fragment_login
    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val googleSignDelegate by inject<GoogleSignDelegate>()

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter =
        Toothpick.openScopes(Scopes.ACTIVITY_SCOPE, scopeName)
            .getInstance(LoginPresenter::class.java)
            .also { Toothpick.closeScope(scopeName) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            presenter.signInGoogle()
        }

        binding.unauthorizedSignIn.setOnClickListener {
            presenter.signInUnauthorized()
        }
    }

    override fun injectDependencies() {
        Toothpick.openScope(Scopes.ACTIVITY_SCOPE).inject(this)
    }

    override fun signIn() {
        val signInIntent: Intent = googleSignDelegate.googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            presenter.handleSignInResult(task)
        } else {
            Toast.makeText(requireContext(), resources.getString(R.string.error_auth), Toast.LENGTH_SHORT).show()
        }
    }
}