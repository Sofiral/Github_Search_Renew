package com.sofiral.github_search_renew.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sofiral.github_search_renew.presentation.bookmark.BookmarkFragment
import com.sofiral.github_search_renew.presentation.core.Config
import com.sofiral.github_search_renew.presentation.search.SearchFragment
import toothpick.Toothpick
import toothpick.ktp.delegate.inject
import javax.inject.Inject

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    @Inject
    lateinit var config: Config

    init {
        Toothpick.openRootScope().inject(this)
    }

    private val authorizedNumTabs = 2
    private val unauthorizedNumTabs = 1

    override fun getItemCount(): Int {
        return if (config.isAuthorized) {
            authorizedNumTabs
        } else {
            unauthorizedNumTabs
        }
    }

    override fun createFragment(position: Int): Fragment {
        return if (config.isAuthorized) {
            when (position) {
                0 -> SearchFragment()
                1 -> BookmarkFragment()
                else -> SearchFragment()
            }
        } else {
            SearchFragment()
        }
    }
}