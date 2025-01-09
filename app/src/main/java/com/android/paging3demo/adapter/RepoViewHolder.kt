package com.android.paging3demo.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.paging3demo.R
import com.android.paging3demo.databinding.RepoViewItemBinding
import com.android.paging3demo.model.Repo

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class RepoViewHolder(private val binding: RepoViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var repo: Repo? = null

    init {
        binding.root.setOnClickListener {
            repo?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                it.context.startActivity(intent)
            }
        }
    }

    fun bind(repo: Repo?) {
        if (repo == null) {
            binding.apply {
                val resources = itemView.resources
                repoName.text = resources.getString(R.string.loading)
                repoDescription.visibility = View.GONE
                repoLanguage.visibility = View.GONE
                repoStars.text = resources.getString(R.string.unknown)
                repoForks.text = resources.getString(R.string.unknown)
            }
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Repo) {
        this.repo = repo
        binding.apply {
            repoName.text = repo.fullName

            // if the description is missing, hide the TextView
            var descriptionVisibility = View.GONE
            if (repo.description != null) {
                repoDescription.text = repo.description
                descriptionVisibility = View.VISIBLE
            }
            repoDescription.visibility = descriptionVisibility

            repoStars.text = repo.stars.toString()
            repoForks.text = repo.forks.toString()

            // if the language is missing, hide the label and the value
            var languageVisibility = View.GONE
            if (!repo.language.isNullOrEmpty()) {
                val resources = root.context.resources
                repoLanguage.text = resources.getString(R.string.language, repo.language)
                languageVisibility = View.VISIBLE
            }
            repoLanguage.visibility = languageVisibility
        }
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            return RepoViewHolder(
                RepoViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
