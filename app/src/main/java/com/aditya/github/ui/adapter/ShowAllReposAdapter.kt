package com.aditya.github.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.github.databinding.RepoItemBinding
import com.aditya.github.model.RepoItem
import com.aditya.github.ui.viewholder.ShowAllReposViewHolder

class ShowAllReposAdapter(private val userName: String?) : RecyclerView.Adapter<ShowAllReposViewHolder>() {
    private var repoList: List<RepoItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAllReposViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = RepoItemBinding.inflate(inflater, parent, false)
        return ShowAllReposViewHolder(dataBinding, userName)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: ShowAllReposViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRepoList(repoList: List<RepoItem>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}