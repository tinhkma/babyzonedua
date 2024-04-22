package com.tinhtx.customapplication.ui.homeFragment.itemView

import android.view.View
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.databinding.ItemBottonHomeBinding
import com.tinhtx.customapplication.model.HomeDto
import com.xwray.groupie.viewbinding.BindableItem

class HomeButtonItem(val homeDto: HomeDto): BindableItem<ItemBottonHomeBinding>() {
    override fun bind(viewBinding: ItemBottonHomeBinding, position: Int) {
        viewBinding.let {
            it.btnButton.text = homeDto.title
            it.btnButton.setOnClickListener { view ->
                homeDto.onClick?.invoke(view)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_botton_home

    override fun initializeViewBinding(view: View): ItemBottonHomeBinding {
        return ItemBottonHomeBinding.bind(view)
    }
}