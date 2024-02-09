package com.sofiral.github_search_renew.presentation.bookmark.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.util.ResourceProvider
import toothpick.Toothpick
import javax.inject.Inject

class BookmarkMarginItemDecoration : RecyclerView.ItemDecoration() {

    @Inject
    lateinit var resourceProvider: ResourceProvider

    init {
        Toothpick.openScope(Scopes.ACTIVITY_SCOPE).inject(this)
    }

    private val bottomDimen by lazy { resourceProvider.getDimension(R.dimen.standard_margin_bottom).toInt() }
    private val sideDimen by lazy { resourceProvider.getDimension(R.dimen.standard_margin_start).toInt() }

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = 0
            }
            left = sideDimen
            right = sideDimen
            bottom = bottomDimen
        }
    }
}