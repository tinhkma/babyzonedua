package com.tinhtx.customapplication.ui.settingFragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import com.tinhtx.customapplication.base.BaseViewModel
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.utils.SingleLiveEvent
import com.xwray.groupie.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : BaseViewModel() {

    private val _dataUser = SingleLiveEvent<User>()
    val dataUser: LiveData<User> = _dataUser

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun updateDataView() {
        _dataUser.postValue(settingRepository.getDataUser().firstOrNull())
    }

    fun insertData(limit: String) {
        viewModelScope.launch(Dispatchers.IO) {
            settingRepository.insertUser(limit)
        }
    }
}