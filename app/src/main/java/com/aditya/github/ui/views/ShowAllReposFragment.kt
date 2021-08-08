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
import com.aditya.github.databinding.FragmentShowAllReposBinding
import com.aditya.github.ui.adapter.ShowAllReposAdapter
import com.aditya.github.ui.viewmodel.ShowAllReposViewModel
import kotlinx.android.synthetic.main.fragment_show_all_repos.*

class ShowAllReposFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentShowAllReposBinding
    private lateinit var adapter: ShowAllReposAdapter
    private var userName: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentShowAllReposBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ShowAllReposFragment).get(ShowAllReposViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userName = arguments?.let { ShowAllReposFragmentArgs.fromBundle(it).userName }

        viewDataBinding.viewmodel?.fetchRepoList(userName)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.showAllReposLiveData?.observe(viewLifecycleOwner, {
            adapter.updateRepoList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = ShowAllReposAdapter(userName)
            val layoutManager = LinearLayoutManager(activity)
            repos_list.layoutManager = layoutManager
            repos_list.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            repos_list.adapter = adapter
        }
    }
}