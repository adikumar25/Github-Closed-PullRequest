package com.aditya.github.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.github.databinding.FragmentShowClosedPullRequestBinding
import com.aditya.github.ui.adapter.ShowClosedPullRequestAdapter
import com.aditya.github.ui.viewmodel.ShowClosedPullRequestViewModel
import kotlinx.android.synthetic.main.fragment_show_closed_pull_request.*

class ShowClosedPullRequestFragment : Fragment() {


    private lateinit var viewDataBinding: FragmentShowClosedPullRequestBinding
    private lateinit var adapter: ShowClosedPullRequestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentShowClosedPullRequestBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ShowClosedPullRequestFragment).get(ShowClosedPullRequestViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repoName = arguments?.let { ShowClosedPullRequestFragmentArgs.fromBundle(it).repoName }
        val userName = arguments?.let { ShowClosedPullRequestFragmentArgs.fromBundle(it).userName }

        viewDataBinding.viewmodel?.fetchClosedPullRequest(userName, repoName)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.showClosedPullRequestLiveData?.observe(viewLifecycleOwner, {
            adapter.updatePullRequestList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = ShowClosedPullRequestAdapter()
            val layoutManager = LinearLayoutManager(activity)
            pullrequest_list.layoutManager = layoutManager
            pullrequest_list.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            pullrequest_list.adapter = adapter
        }
    }
}