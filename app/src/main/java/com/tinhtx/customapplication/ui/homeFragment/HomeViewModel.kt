package com.tinhtx.customapplication.ui.homeFragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import com.tinhtx.customapplication.base.BaseViewModel
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.utils.SingleLiveEvent
import com.xwray.groupie.Group
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

    private val _dataHeader = SingleLiveEvent<Pair<User?, List<DailyExpense>?>>()
    val dataHeader: LiveData<Pair<User?, List<DailyExpense>?>> = _dataHeader

    private val _dataType = SingleLiveEvent<List<ExpenseType>>()
    val dataType: LiveData<List<ExpenseType>> = _dataType

    private val _dataByType = SingleLiveEvent<ExpenseType>()
    val dataByType: LiveData<ExpenseType> = _dataByType

    private val _dataDailyExpense = SingleLiveEvent<List<DailyExpense>>()
    val dataDailyExpense: LiveData<List<DailyExpense>> = _dataDailyExpense

    private val _dataDailyExpenseByType = SingleLiveEvent<DailyExpense>()
    val dataDailyExpenseByType: LiveData<DailyExpense> = _dataDailyExpenseByType

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun updateDataView() {
        getDataUser()
        //getDataExpense()
        getDataType()
    }

    fun insertType(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.insertType(type)
            delay(100)
            getDataType()
        }
    }

    fun updateDataUser(user: User) {
        homeRepository.updateData(user)
    }

    fun getDataUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataUser.postValue(homeRepository.getDataUser())
            updateDataHeader()
        }
    }

    fun getDataType() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataType.postValue(homeRepository.getAllType())
        }
    }

    fun getDataExpense() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataDailyExpense.postValue(homeRepository.getAllExpenses())
            updateDataHeader()
        }
    }

    fun updateDataDate(data: String) {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.updateDataDate(data)
        }
    }

    fun updateDataDailyExpense(dailyExpense: DailyExpense) {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.insertDataExpenses(dailyExpense)
        }
    }

    private fun updateDataHeader() {
        indexUpdateHeader++
        if (indexUpdateHeader == sumUpdateHeader) {
            val data = Pair(dataUser.value?.firstOrNull(), dataDailyExpense.value)
            _dataHeader.postValue(data)
            indexUpdateHeader = 0
        }
    }
}