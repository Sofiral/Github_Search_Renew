package com.sofiral.github_search_renew.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.sofiral.github_search_renew.R
import com.sofiral.github_search_renew.databinding.FragmentDetailBinding
import com.sofiral.github_search_renew.di.Scopes
import com.sofiral.github_search_renew.presentation.core.CoreFragment
import com.sofiral.github_search_renew.presentation.detail.model.DetailFragmentArgs
import com.sofiral.github_search_renew.presentation.detail.model.DetailRepoUiModel
import com.sofiral.github_search_renew.util.module
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick


class DetailFragment : CoreFragment(), DetailView {

    override val layoutRes: Int = R.layout.fragment_detail
    private val binding by viewBinding(FragmentDetailBinding::bind)

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    @ProvidePresenter
    fun providePresenter(): DetailPresenter =
        Toothpick.openScopes(Scopes.ACTIVITY_SCOPE, scopeName)
            .module {
                bind(DetailFragmentArgs::class.java)
                    .toInstance(requireArguments().getSerializable(ARGUMENTS_KEY) as? DetailFragmentArgs)
            }
            .getInstance(DetailPresenter::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cbBookmark.setOnClickListener {
            if (binding.cbBookmark.isChecked) {
                presenter.addBookmark()
            } else {
                presenter.deleteBookmark()
            }
        }
    }

    override fun showDetailRepo(repo: DetailRepoUiModel, isUserAuthorized: Boolean) {
        with(binding) {
            cbBookmark.isVisible = isUserAuthorized

            tvRepoName.text = repo.name
            tvAuthor.text = repo.owner.login

            tvRepoDescription.text = repo.description
            tvRepoDescription.isVisible = repo.description.isNotEmpty()

            tvCreationDate.text = repo.creationDate
            tvStars.text = repo.stars
            tvForks.text = repo.forks

            Glide.with(this@DetailFragment)
                .load(repo.owner.avatarUrl)
                .into(image)
        }
    }

    override fun showSuccessResult(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARGUMENTS_KEY = "ARGUMENTS_KEY"

        fun newInstance(args: DetailFragmentArgs) = DetailFragment().apply {
            arguments = Bundle(1).apply {
                putSerializable(ARGUMENTS_KEY, args)
            }
        }
    }
}