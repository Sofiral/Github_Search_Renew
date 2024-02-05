package com.sofiral.github_search_renew.presentation.delegate

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import javax.inject.Inject

class GoogleSignDelegate @Inject constructor(
    private val context: Context
) {
    private var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    var googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)
        private set
}