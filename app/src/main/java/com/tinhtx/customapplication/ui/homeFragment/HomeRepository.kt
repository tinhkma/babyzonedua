package com.tinhtx.customapplication.ui.homeFragment

import android.content.Context
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.dao.DailyExpensesDao
import com.tinhtx.customapplication.dao.ExpenseTypeDao
import com.tinhtx.customapplication.dao.UserDao
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.model.HomeDto
import com.tinhtx.customapplication.ui.historyFragment.HistoryHeaderItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeButtonItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeDropInputItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeInputItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeSpaceItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeTitleItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeViewType
import com.tinhtx.customapplication.utils.Strings
import com.tinhtx.customapplication.utils.hideSoftKeyboard
import com.xwray.groupie.Group
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val userDao: UserDao,
    private val dailyExpensesDao: DailyExpensesDao,
    private val expenseTypeDao: ExpenseTypeDao
) {
    private val _dataHeader = BehaviorSubject.create<Pair<List<DailyExpense>?, List<User>?>>()
    val dataHeader: Observable<Pair<List<DailyExpense>?, List<User>?>> = _dataHeader.hide()

    private val _dataDailyExpense = BehaviorSubject.create<List<DailyExpense>>()
    val dataDailyExpense: Observable<List<DailyExpense>> = _dataDailyExpense.hide()

    private val _onUpdateDone = BehaviorSubject.create<Unit>()
    val onUpdateDone: Observable<Unit> = _onUpdateDone.hide()

    private val _dataType = BehaviorSubject.create<List<ExpenseType>>()
    val dataType: Observable<List<ExpenseType>> = _dataType.hide()

    private val _dataUser = BehaviorSubject.create<List<User>>()
    val dataUser: Observable<List<User>> = _dataUser.hide()

    var sum = 2
    var count = 0

    fun updateData(user: User) {
        userDao.insertAll(user)
    }

    fun getDataUser() {
        _dataUser.onNext(userDao.getAll())
        getDataDone()
    }

    fun getAllType() {
        _dataType.onNext(expenseTypeDao.getAll())
    }

    fun insertType(type: String) {
        val expenseType = ExpenseType(type = type)
        if (!expenseType.type.isNullOrEmpty()) {
            _onUpdateDone.onNext(Unit)
            expenseTypeDao.insertAll(expenseType)
        }
    }

    fun getAllExpenses() {
        _dataDailyExpense.onNext(dailyExpensesDao.getAll())
        getDataDone()
    }

    fun insertDataExpenses(expense: DailyExpense) {
        dailyExpensesDao.insertAll(expense)
    }

    fun updateDataUser(limit: String) {
        if (limit.isNotEmpty()) {
            val user = _dataUser.value?.firstOrNull()
            val newUser = User(
                uid = user?.uid ?: 0,
                limit = limit,
                firstName = user?.firstName ?: "",
                lastName = user?.lastName ?: ""
            )
            _onUpdateDone.onNext(Unit)
            userDao.insertAll(newUser)
        }
    }

    private fun getDataDone() {
        count++
        if (count == sum) {
            _dataHeader.onNext(Pair(_dataDailyExpense.value, _dataUser.value))
            count = 0
        }
    }
}