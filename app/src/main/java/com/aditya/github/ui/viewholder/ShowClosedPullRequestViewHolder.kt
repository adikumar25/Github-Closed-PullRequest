package com.aditya.github.ui.viewholder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.aditya.github.BR
import com.aditya.github.model.PullRequestItem
import com.aditya.github.utils.DateUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pullrequest_item.view.*

class ShowClosedPullRequestViewHolder constructor(private val dataBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(pullRequestItem: PullRequestItem) {
        dataBinding.setVariable(BR.pullRequestItem, pullRequestItem)
        dataBinding.executePendingBindings()

        itemView.item_description.visibility = if (pullRequestItem.body == "") View.GONE else View.VISIBLE

        itemView.created_at.text = DateUtils.getFormattedDate(pullRequestItem.created_at)
        itemView.closed_at.text = DateUtils.getFormattedDate(pullRequestItem.closed_at)

        Picasso.get().load(pullRequestItem.user.avatar_url).into(itemView.item_user_image)
    }
}