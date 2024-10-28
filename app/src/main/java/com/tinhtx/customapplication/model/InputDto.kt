package com.tinhtx.customapplication.model

import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeViewType

data class InputDto(
    var type: HomeViewType,
    var dataProduct: String? = null,
    var dataPrice: String? = null,
    var dataDate: String? = null,
    var limit: String? = null,
    var name: String? = null,
    var otherType: String? = null,
    var dataType: String? = null
)
