package com.tinhtx.customapplication.ui.homeFragment.itemView

import android.view.View
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.databinding.ItemTitleHomeBinding
import com.xwray.groupie.viewbinding.BindableItem

class HomeTitleItem(var title: String) : BindableItem<ItemTitleHomeBinding>() {
    override fun bind(viewBinding: ItemTitleHomeBinding, position: Int) {
        viewBinding.let {
            it.titleScreen.text = title
        }
    }

    override fun getLayout(): Int = R.layout.item_title_home

    override fun initializeViewBinding(view: View): ItemTitleHomeBinding {
        return ItemTitleHomeBinding.bind(view)
    }
}