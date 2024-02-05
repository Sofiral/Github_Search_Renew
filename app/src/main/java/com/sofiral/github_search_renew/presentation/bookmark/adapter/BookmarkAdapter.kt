package com.sofiral.github_search_renew.presentation.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sofiral.github_search_renew.databinding.ItemBookmarkBinding
import com.sofiral.github_search_renew.presentation.bookmark.model.BookmarkUiModel
import com.sofiral.github_search_renew.util.show

class BookmarkAdapter(
    private val onBookmarkCheckedListener: (Long) -> Unit,
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val bookmarks: MutableList<BookmarkUiModel> = arrayListOf()

    fun updateBookmarks(bookmarks: List<BookmarkUiModel>) {
        this.bookmarks.clear()
        this.bookmarks.addAll(bookmarks)
        notifyDataSetChanged()
    }

    fun removeItem(id: Long) {
        val position = bookmarks.indexOfFirst { it.id == id }

        bookmarks.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class BookmarkViewHolder(
        private val binding: ItemBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bookmark: BookmarkUiModel) {
            with(binding) {
                tvRepoDescription.text = bookmark.description
                tvRepoDescription.isVisible = bookmark.description.isNotEmpty()

                tvAuthor.text = bookmark.login
                tvRepoName.text = bookmark.name

                tvCreationDate.text = bookmark.creationDate
                tvStars.text = bookmark.stars
                tvForks.text = bookmark.forks
                setupImage(bookmark.avatarUrl)

                binding.cbBookmark.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked.not()) {
                        onBookmarkCheckedListener.invoke(bookmark.id)
                    }
                }
            }
        }

        private fun setupImage(avatarUrl: String) {
            binding.imageWrapper.show()
            Glide
                .with(itemView.context)
                .asBitmap()
                .load(avatarUrl)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(bookmarks[position])
    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }
}