package com.tinhtx.customapplication.ui.activity

import androidx.lifecycle.LiveData
import com.tinhtx.customapplication.base.BaseViewModel
import com.tinhtx.customapplication.utils.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): BaseViewModel() {
    private val _dataUser = SingleLiveEvent<List<String>>()
    val dataUser: LiveData<List<String>> = _dataUser

    fun getUsers(): LiveData<List<String>> {
        return dataUser
    }

    fun loadUser() {
        val data = mutableListOf<String>()
        data.add("Truong Xuan Tinh")
        data.add("Pham Thi Quyen")
        _dataUser.postValue(data)
    }
}