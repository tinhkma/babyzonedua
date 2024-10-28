package com.tinhtx.customapplication.ui.homeFragment

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import com.tinhtx.customapplication.base.BaseViewModel
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private var indexUpdateHeader: Int = 0
    private var sumUpdateHeader: Int = 2

    private val _dataUser = SingleLiveEvent<List<User>>()
    val dataUser: LiveData<List<User>> = _dataUser

    private val _dataType = SingleLiveEvent<List<ExpenseType>>()
    val dataType: LiveData<List<ExpenseType>> = _dataType

    private val _onUpdateDone = SingleLiveEvent<Unit>()
    val onUpdateDone: LiveData<Unit> = _onUpdateDone

    private val _dataHeader = SingleLiveEvent<Pair<List<DailyExpense>?, List<User>?>>()
    val dataHeader: LiveData<Pair<List<DailyExpense>?, List<User>?>> = _dataHeader

    private val _dataDailyExpense = SingleLiveEvent<List<DailyExpense>>()
    val dataDailyExpense: LiveData<List<DailyExpense>> = _dataDailyExpense

    init {
        homeRepository.apply {
            onUpdateDone.subscribe {
                _onUpdateDone.postValue(Unit)
            }
            dataType.subscribe {
                _dataType.postValue(it)
            }
            dataUser.subscribe {
                _dataUser.postValue(it)
            }
            dataDailyExpense.subscribe {
                _dataDailyExpense.postValue(it)
            }
            dataDailyExpense.subscribe {
                _dataDailyExpense.postValue(it)
            }
            dataHeader.subscribe {
                _dataHeader.postValue(it)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun updateDataView() {
        getAllDataUser()
        getAllDataExpense()
        getAllType()
    }

    fun insertType(type: String) {
        homeRepository.insertType(type)
    }

    fun updateDataUser(limit: String) {
        homeRepository.updateDataUser(limit)
    }

    fun getAllDataUser() {
        homeRepository.getDataUser()
    }

    fun getAllType() {
        homeRepository.getAllType()
    }

    fun getAllDataExpense() {
        homeRepository.getAllExpenses()
    }

    fun updateDataDailyExpense(dailyExpense: DailyExpense) {
        homeRepository.insertDataExpenses(dailyExpense)
    }
}