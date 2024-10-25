package com.tinhtx.customapplication.ui.welcomeScreen

import androidx.lifecycle.viewModelScope
import com.tinhtx.customapplication.base.BaseViewModel
import com.tinhtx.customapplication.dao.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val welcomeRepository: WelcomeRepository
) : BaseViewModel() {

    fun insertData(name: String) {
        viewModelScope.launch(Dispatchers.Main) {
            welcomeRepository.insertUser(User(uid = 0, limit = "", fullName = name))
        }
    }
}