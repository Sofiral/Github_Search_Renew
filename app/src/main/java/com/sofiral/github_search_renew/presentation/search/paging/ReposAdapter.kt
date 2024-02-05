package com.sofiral.github_search_renew.presentation.search.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.databinding.RepoViewItemBinding
import com.sofiral.github_search_renew.presentation.search.model.RepoSearchUiModel

class ReposAdapter(
    private val onRepoClicked: (Long) -> Unit
) : PagingDataAdapter<RepoSearchUiModel, ReposAdapter.RepoViewHolder>(DIFF_CALLBACK) {

    inner class RepoViewHolder(private val binding: RepoViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RepoSearchUiModel) {
            with(binding) {
                repoName.text = repo.fullName

                repoDescription.text = repo.description
                repoDescription.isVisible = repo.description.isNotEmpty()

                repoLanguage.text = itemView.context.getString(R.string.language, repo.language)
                repoLanguage.isVisible = repo.language.isNotEmpty()

                root.setOnClickListener {
                    onRepoClicked.invoke(repo.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            RepoViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoSearchUiModel>() {
            override fun areItemsTheSame(oldItem: RepoSearchUiModel, newItem: RepoSearchUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RepoSearchUiModel, newItem: RepoSearchUiModel): Boolean =
                oldItem == newItem
        }
    }
}