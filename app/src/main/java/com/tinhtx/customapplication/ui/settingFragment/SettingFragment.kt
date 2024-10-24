package com.tinhtx.customapplication.ui.settingFragment

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseFragment
import com.tinhtx.customapplication.base.showToast
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.databinding.FragmentSettingBinding
import com.tinhtx.customapplication.utils.Strings
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModelClass: Class<SettingViewModel> = SettingViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_setting
    private lateinit var recyclerView: RecyclerView
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onDataBound(binding: FragmentSettingBinding) {
        binding.let {
            it.viewModel = viewModel
            it.rcvSettings.setupRcv()
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.let {
                it.dataView.observe(this@SettingFragment) {
                    groupAdapter.update(it)
                }
                it.onActionDone.observe(this@SettingFragment) {
                    viewModel.insertData(
                        ExpenseType(type = it.type),
                        it.name ?: Strings.EMPTY,
                        it.limit ?: Strings.EMPTY
                    )
                    showToast("Update done data!")
                }
            }
        }
    }

    private fun RecyclerView.setupRcv() {
        adapter = groupAdapter.apply {
            setOnItemClickListener { item, _ ->
                //listener.onClickItem()
            }
        }
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        layoutManager = linearLayoutManager

    }
}