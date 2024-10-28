package com.tinhtx.customapplication.model

import android.view.View
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeViewType

data class HomeDto(
    var homeViewType: HomeViewType,
    var title: String? = null,
    var hint: String? = null,
    var isEnable: Boolean = true,
    var onClick: ((View?) -> Unit)? = null,
    var onChangeDone: ((InputDto) -> Unit)? = null,
    var dataType: List<ExpenseType>? = null
)
