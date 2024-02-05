package com.sofiral.github_search_renew.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.databinding.FragmentSearchBinding
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.presentation.core.CoreFragment
import com.sofiral.github_search_renew.presentation.search.model.RepoSearchUiModel
import com.sofiral.github_search_renew.presentation.search.paging.ReposAdapter
import com.sofiral.github_search_renew.presentation.search.paging.ReposLoadStateAdapter
import com.sofiral.github_search_renew.util.gone
import com.sofiral.github_search_renew.util.hideKeyboard
import com.sofiral.github_search_renew.util.setAdapter
import com.sofiral.github_search_renew.util.viewLifecycle
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class SearchFragment : CoreFragment(), SearchView {
    override val layoutRes: Int = R.layout.fragment_search

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val adapter: ReposAdapter by lazy { ReposAdapter(presenter::onRepoClicked) }

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter =
        Toothpick.openScopes(Scopes.ACTIVITY_SCOPE, scopeName)
            .getInstance(SearchPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchListener()
        initRecyclerView()
    }

    private fun initSearchListener() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    presenter.onQuerySubmit(query)
                    hideKeyboard()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.onQueryChanged(newText.orEmpty())
                return true
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvRepoList.setAdapter(
            adapter.withLoadStateFooter(ReposLoadStateAdapter { adapter.retry() }), viewLifecycle
        )

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvRepoList.addItemDecoration(decoration)

        adapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

            with(binding) {
                emptyList.isVisible = isListEmpty
                rvRepoList.isVisible = !isListEmpty && loadState.source.refresh !is LoadState.Loading
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    override fun updateAdapter(data: PagingData<RepoSearchUiModel>) {
        adapter.submitData(lifecycle, data)
    }

    override fun clearViews() {
        binding.emptyList.gone()
        binding.progressBar.gone()
        binding.retryButton.gone()
        binding.rvRepoList.gone()
    }
}