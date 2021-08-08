package com.aditya.github.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.github.databinding.PullrequestItemBinding
import com.aditya.github.model.PullRequestItem
import com.aditya.github.ui.viewholder.ShowClosedPullRequestViewHolder

class ShowClosedPullRequestAdapter : RecyclerView.Adapter<ShowClosedPullRequestViewHolder>() {
    private var pullRequestList: List<PullRequestItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowClosedPullRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = PullrequestItemBinding.inflate(inflater, parent, false)
        return ShowClosedPullRequestViewHolder(dataBinding)
    }

    override fun getItemCount() = pullRequestList.size

    override fun onBindViewHolder(holder: ShowClosedPullRequestViewHolder, position: Int) {
        holder.setup(pullRequestList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePullRequestList(repoList: List<PullRequestItem>) {
        this.pullRequestList = repoList
        notifyDataSetChanged()
    }
}