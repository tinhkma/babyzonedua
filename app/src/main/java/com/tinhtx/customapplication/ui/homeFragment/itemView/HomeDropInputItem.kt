package com.tinhtx.customapplication.ui.homeFragment.itemView

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.databinding.ItemDropDataInputHomeBinding
import com.tinhtx.customapplication.model.HomeDto
import com.tinhtx.customapplication.model.InputDto
import com.tinhtx.customapplication.utils.Strings
import com.xwray.groupie.viewbinding.BindableItem

class HomeDropInputItem(val homeDto: HomeDto, val context: Context) :
    BindableItem<ItemDropDataInputHomeBinding>() {
    override fun bind(viewBinding: ItemDropDataInputHomeBinding, position: Int) {
        viewBinding.let {
            it.typeDropDown.hint = homeDto.hint
            val listData = homeDto.dataType?.map { it.type }
            if (!listData.isNullOrEmpty()) {
                val adapter = ArrayAdapter(
                    context, R.layout.list_item_dropdown, listData
                )
                (it.typeDropDown as? AutoCompleteTextView)?.setAdapter(adapter)
                it.typeDropDown.setOnItemClickListener { _, _, i, _ ->
                    homeDto.onChangeDone?.invoke(
                        InputDto(
                            homeDto.homeViewType, dataType = listData[i] ?: Strings.EMPTY
                        )
                    )
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_drop_data_input_home

    override fun initializeViewBinding(view: View): ItemDropDataInputHomeBinding {
        return ItemDropDataInputHomeBinding.bind(view)
    }
}