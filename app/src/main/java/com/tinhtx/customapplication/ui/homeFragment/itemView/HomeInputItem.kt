package com.tinhtx.customapplication.ui.homeFragment.itemView

import android.text.InputType
import android.view.View
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.databinding.ItemDataInputHomeBinding
import com.tinhtx.customapplication.model.HomeDto
import com.tinhtx.customapplication.model.InputDto
import com.xwray.groupie.viewbinding.BindableItem

class HomeInputItem(val homeDto: HomeDto) : BindableItem<ItemDataInputHomeBinding>() {
    override fun bind(viewBinding: ItemDataInputHomeBinding, position: Int) {
        viewBinding.let {
            it.tilTitle.hint = homeDto.hint
            it.viewFocus.visibility = View.GONE
            it.edtTitle.isEnabled = homeDto.isEnable
            if (homeDto.homeViewType == HomeViewType.DATE) {
                it.edtTitle.isEnabled = false
                it.viewFocus.visibility = View.VISIBLE
                if (!homeDto.title.isNullOrEmpty()) it.edtTitle.setText(homeDto.title)
            }
            if (homeDto.homeViewType == HomeViewType.LIMIT) {
                if (!homeDto.title.isNullOrEmpty()) it.edtTitle.setText(homeDto.title)
                it.edtTitle.inputType = InputType.TYPE_CLASS_NUMBER
            }
            if (homeDto.homeViewType == HomeViewType.TYPE) it.edtTitle.inputType =
                InputType.TYPE_NULL
            if (homeDto.homeViewType == HomeViewType.PRICE) it.edtTitle.inputType =
                InputType.TYPE_CLASS_NUMBER
            it.edtTitle.setOnFocusChangeListener { view, hasFocus ->
                if (!hasFocus) {
                    homeDto.onChangeDone?.invoke(
                        InputDto(
                            type = homeDto.homeViewType,
                            dataDate = if (homeDto.homeViewType == HomeViewType.DATE) it.edtTitle.text.toString() else null,
                            dataPrice = if (homeDto.homeViewType == HomeViewType.PRICE) it.edtTitle.text.toString() else null,
                            dataProduct = if (homeDto.homeViewType == HomeViewType.PRODUCT) it.edtTitle.text.toString() else null,
                            name = if (homeDto.homeViewType == HomeViewType.NAME) it.edtTitle.text.toString() else null,
                            otherType = if (homeDto.homeViewType == HomeViewType.TYPE_OTHER) it.edtTitle.text.toString() else null,
                            limit = if (homeDto.homeViewType == HomeViewType.LIMIT) it.edtTitle.text.toString() else null
                        )
                    )
                }
            }
            it.viewFocus.setOnClickListener {
                homeDto.onClick?.invoke(it)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_data_input_home

    override fun initializeViewBinding(view: View): ItemDataInputHomeBinding {
        return ItemDataInputHomeBinding.bind(view)
    }
}