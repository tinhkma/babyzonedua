package com.tinhtx.customapplication.ui.historyFragment

import androidx.lifecycle.LiveData
import com.tinhtx.customapplication.base.BaseViewModel
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.utils.SingleLiveEvent
import com.xwray.groupie.Group
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    val historyRepository: HistoryRepository
): BaseViewModel() {

    private val _updateData = SingleLiveEvent<List<Group>>()
    val updateData: LiveData<List<Group>> = _updateData

    fun getData() {
        _updateData.postValue(historyRepository.getAllExpenses())
    }
}