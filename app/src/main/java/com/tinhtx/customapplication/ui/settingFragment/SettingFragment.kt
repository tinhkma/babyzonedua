package com.tinhtx.customapplication.ui.settingFragment

import androidx.lifecycle.lifecycleScope
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseFragment
import com.tinhtx.customapplication.base.showToast
import com.tinhtx.customapplication.databinding.FragmentSettingBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModelClass: Class<SettingViewModel> = SettingViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_setting

    override fun onDataBound(binding: FragmentSettingBinding) {
        binding.let {
            it.viewModel = viewModel
            it.btnButtonChangeLimit.setOnClickListener {
                showToast("Update done data!")
                viewModel.insertData(binding.edtLimit.text.toString())
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.let {
                it.dataUser.observe(this@SettingFragment) {
                    if (it.limit?.isNotEmpty() == true) {
                        binding.btnButtonChangeLimit.setText("Update Limit")
                        binding.edtLimit.setText(it.limit)
                    } else {
                        binding.btnButtonChangeLimit.setText("Set Limit")
                    }
                }
            }
        }
    }
}