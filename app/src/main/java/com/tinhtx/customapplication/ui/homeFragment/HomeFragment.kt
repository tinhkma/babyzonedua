package com.tinhtx.customapplication.ui.homeFragment

import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseFragment
import com.tinhtx.customapplication.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_home

    override fun onDataBound(binding: FragmentHomeBinding) {
        binding.viewModel = viewModel

        viewModel.let {

        }
    }
}