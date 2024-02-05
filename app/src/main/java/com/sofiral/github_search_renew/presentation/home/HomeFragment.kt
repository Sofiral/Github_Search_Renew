package com.sofiral.github_search_renew.presentation.home

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.databinding.FragmentHomeBinding
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.presentation.core.CoreFragment
import com.sofiral.github_search_renew.util.setAdapter
import com.sofiral.github_search_renew.util.viewLifecycle
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class HomeFragment : CoreFragment() {
    override val layoutRes: Int = R.layout.fragment_home
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()
    }


    private fun initTabs() {
        val tabLayout = binding.tabLayout
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        val viewPager = binding.pager.apply {
            setAdapter(adapter, viewLifecycle)
            isSaveEnabled = false
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.tab_names)[position]
        }.attach()
    }
}