package com.tinhtx.customapplication.ui.historyFragment

import android.view.View
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.databinding.ItemHeaderHistoryBinding
import com.tinhtx.customapplication.utils.formatValue
import com.xwray.groupie.viewbinding.BindableItem

class HistoryHeaderItem(val usedAmount: String, val nowAvailable: String) :
    BindableItem<ItemHeaderHistoryBinding>() {
    override fun bind(viewBinding: ItemHeaderHistoryBinding, position: Int) {
        viewBinding.let {
            if (usedAmount != "-" && nowAvailable != "-") {
                it.availableAmount.text = nowAvailable.formatValue()
                it.usedAmount.text = usedAmount.formatValue()
                it.availableBalancesAmount.text =
                    (nowAvailable.toDouble() - usedAmount.toDouble()).toString().formatValue()
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_header_history

    override fun initializeViewBinding(view: View): ItemHeaderHistoryBinding {
        return ItemHeaderHistoryBinding.bind(view)
    }
}