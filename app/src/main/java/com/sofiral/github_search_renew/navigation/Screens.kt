package com.sofiral.github_search_renew.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.sofiral.github_search_renew.presentation.detail.DetailFragment
import com.sofiral.github_search_renew.presentation.detail.model.DetailFragmentArgs
import com.sofiral.github_search_renew.presentation.home.HomeFragment
import com.sofiral.github_search_renew.presentation.login.LoginFragment

object Screens {
    fun Main() = FragmentScreen { LoginFragment() }

    fun Home() = FragmentScreen { HomeFragment() }

    fun Detail(args: DetailFragmentArgs) = FragmentScreen { DetailFragment.newInstance(args) }
}