package com.aditya.github.ui.viewholder

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aditya.github.BR
import com.aditya.github.R
import com.aditya.github.model.RepoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_item.view.*

class ShowAllReposViewHolder constructor(private val dataBinding: ViewDataBinding, private val userName: String?) : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(repoItem: RepoItem) {
        dataBinding.setVariable(BR.repoItem, repoItem)
        dataBinding.executePendingBindings()

        Picasso.get().load(repoItem.owner.avatar_url).into(itemView.item_avatar)

        itemView.item_description.visibility = if (repoItem.description == "") View.GONE else View.VISIBLE

        itemView.setOnClickListener {
            val bundle = bundleOf("userName" to userName, "repoName" to repoItem.name)
            if (itemView.findNavController().currentDestination?.id == R.id.showAllReposFragment) {
                itemView.findNavController().navigate(R.id.action_showAllReposFragment_to_showClosedPullRequestFragment, bundle)
            }
        }
    }
}