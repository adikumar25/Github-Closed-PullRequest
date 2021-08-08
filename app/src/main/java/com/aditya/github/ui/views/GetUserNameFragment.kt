package com.aditya.github.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.aditya.github.R
import com.aditya.github.databinding.FragmentGetUserNameBinding
import com.aditya.github.ui.viewmodel.GetUserNameViewModel
import kotlinx.android.synthetic.main.fragment_get_user_name.*

class GetUserNameFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentGetUserNameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentGetUserNameBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@GetUserNameFragment).get(GetUserNameViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.isClicked?.observe(viewLifecycleOwner) {
            val userName = input_user_name.text

            if (userName.isNullOrBlank()) {
                input_user_name.error = "Please provide user name"
                return@observe
            }

            val bundle = bundleOf("userName" to userName.toString())

            if (findNavController(this).currentDestination?.id == R.id.getUserNameFragment) {
                findNavController(this).navigate(
                    R.id.action_getUserNameFragment_to_showAllReposFragment,
                    bundle
                )
            }
        }
    }
}