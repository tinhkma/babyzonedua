package com.tinhtx.customapplication.ui.historyFragment

import android.view.View
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.databinding.ItemHistoryBinding
import com.tinhtx.customapplication.utils.formatValue
import com.xwray.groupie.viewbinding.BindableItem

class HistoryBillItem(val dailyExpense: DailyExpense) : BindableItem<ItemHistoryBinding>() {
    override fun bind(viewBinding: ItemHistoryBinding, position: Int) {
        viewBinding.let {
            val titleHeader = "${dailyExpense.type?.type} - ${dailyExpense.date}"
            val description = "Buy ${dailyExpense.product} a total of ${dailyExpense.price?.formatValue()} VND."
            it.tvProduct.text = description
            it.tvProductTitle.text = titleHeader
        }
    }

    override fun getLayout(): Int = R.layout.item_history

    override fun initializeViewBinding(view: View): ItemHistoryBinding {
        return ItemHistoryBinding.bind(view)
    }
}