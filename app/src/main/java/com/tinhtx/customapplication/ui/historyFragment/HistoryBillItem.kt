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
            it.tvDate.text = dailyExpense.date
            it.tvPrice.text = dailyExpense.price?.formatValue()
            it.tvProduct.text = dailyExpense.product
            it.tvType.text = dailyExpense.type?.type
        }
    }

    override fun getLayout(): Int = R.layout.item_history

    override fun initializeViewBinding(view: View): ItemHistoryBinding {
        return ItemHistoryBinding.bind(view)
    }
}