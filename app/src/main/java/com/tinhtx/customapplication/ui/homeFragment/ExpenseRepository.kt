package com.tinhtx.customapplication.ui.homeFragment

import androidx.lifecycle.LiveData
import com.xwray.groupie.Group

/**
 * @author: tinhtx on 25,April,2024
 */
interface ExpenseRepository {

    fun setupViewData(): LiveData<List<Group>>

    fun actionView(): LiveData<Unit>
}