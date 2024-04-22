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
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private val _updateData = SingleLiveEvent<DailyExpense>()
    val updateData: LiveData<DailyExpense> = _updateData

    private val _onActionDate = SingleLiveEvent<Unit>()
    val onActionDate: LiveData<Unit> = _onActionDate

    init {
        disposables.addAll(
            homeRepository.updateData.subscribe {
                _updateData.postValue(it)
            },
            homeRepository.onActionDate.subscribe {
                _onActionDate.postValue(Unit)
            }
        )
    }

    private val _dataUser = SingleLiveEvent<List<User>>()
    val dataUser: LiveData<List<User>> = _dataUser

    private val _dataView = SingleLiveEvent<List<Group>>()
    val dataView: LiveData<List<Group>> = _dataView

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
        _dataView.postValue(homeRepository.setupViewData())
    }

    fun updateDataUser(user: User) {
        homeRepository.updateData(user)
    }
    private fun getDataUser() {
        viewModelScope.launch(Dispatchers.Main) {
            _dataUser.postValue(homeRepository.getDataUser())
        }
    }

    fun getDataType() {
        viewModelScope.launch(Dispatchers.Main) {
            _dataType.postValue(homeRepository.getAllType())
        }
    }

    fun getDataExpense() {
        viewModelScope.launch(Dispatchers.Main) {
            _dataDailyExpense.postValue(homeRepository.getAllExpenses())
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
}