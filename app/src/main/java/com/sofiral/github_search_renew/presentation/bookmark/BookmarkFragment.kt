package com.sofiral.github_search_renew.presentation.bookmark

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.databinding.FragmentBookmarkBinding
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.presentation.bookmark.adapter.BookmarkAdapter
import com.sofiral.github_search_renew.presentation.bookmark.adapter.BookmarkMarginItemDecoration
import com.sofiral.github_search_renew.presentation.bookmark.model.BookmarkUiModel
import com.sofiral.github_search_renew.presentation.core.CoreFragment
import com.sofiral.github_search_renew.util.gone
import com.sofiral.github_search_renew.util.show
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick

class BookmarkFragment : CoreFragment(), BookmarkView {

    override val layoutRes: Int = R.layout.fragment_bookmark
    private val binding by viewBinding(FragmentBookmarkBinding::bind)

    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter(
            onBookmarkCheckedListener = presenter::deleteBookmark
        )
    }

    @InjectPresenter
    lateinit var presenter: BookmarkPresenter

    @ProvidePresenter
    fun providePresenter(): BookmarkPresenter =
        Toothpick.openScopes(Scopes.ACTIVITY_SCOPE, scopeName)
            .getInstance(BookmarkPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        binding.btDeleteAll.setOnClickListener {
            presenter.deleteAllBookmarks()
        }
    }

    private fun initRecyclerView() {
        binding.rvBookmark.apply {
            adapter = bookmarkAdapter
            addItemDecoration(BookmarkMarginItemDecoration())
        }
    }

    override fun updateBookmarks(data: List<BookmarkUiModel>) {
        bookmarkAdapter.updateBookmarks(data)
    }

    override fun showAbsenceBookmarks() {
        binding.tvNoBookmarks.show()
        binding.btDeleteAll.gone()
    }

    override fun deleteSpecificBookmark(bookmarkId: Long) {
        bookmarkAdapter.removeItem(bookmarkId)
    }
}