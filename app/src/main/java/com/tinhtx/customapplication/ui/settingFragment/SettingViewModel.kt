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

    private val _dataView = SingleLiveEvent<List<Group>>()
    val dataView: LiveData<List<Group>> = _dataView

    private val _onActionDone = SingleLiveEvent<SettingDto>()
    val onActionDone: LiveData<SettingDto> = _onActionDone

    init {
        disposables.addAll(
            settingRepository.onActionDone.subscribe {
                _onActionDone.postValue(it)
            }
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun updateDataView() {
        _dataView.postValue(settingRepository.setupViewData())
    }

    fun insertData(type: ExpenseType, name: String, limit: String) {
        viewModelScope.launch(Dispatchers.Main) {
            settingRepository.insertType(type)
            settingRepository.insertUser(User(uid = 0, limit = limit, fullName = name))
        }
    }
}