package com.tinhtx.customapplication.ui.homeFragment.itemView

import android.view.View
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.databinding.ItemSpaceHomeBinding
import com.xwray.groupie.viewbinding.BindableItem

class HomeSpaceItem : BindableItem<ItemSpaceHomeBinding>() {
    override fun bind(viewBinding: ItemSpaceHomeBinding, position: Int) {}

    override fun getLayout(): Int = R.layout.item_space_home

    override fun initializeViewBinding(view: View): ItemSpaceHomeBinding {
        return ItemSpaceHomeBinding.bind(view)
    }
}