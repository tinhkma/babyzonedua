package com.tinhtx.customapplication.ui.historyFragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseFragment
import com.tinhtx.customapplication.databinding.FragmentHistoryBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class HistoryFragment: BaseFragment<FragmentHistoryBinding, HistoryViewModel>() {
    override val viewModelClass: Class<HistoryViewModel> = HistoryViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_history
    private lateinit var recyclerView: RecyclerView
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onDataBound(binding: FragmentHistoryBinding) {
        binding.let {
            it.viewModel = viewModel
            it.rcvHistory.setupRcv()
        }
        viewModel.getData()
        viewModel.updateData.observe(this) {
            groupAdapter.update(it)
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