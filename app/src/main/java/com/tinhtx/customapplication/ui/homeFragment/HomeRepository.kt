package com.tinhtx.customapplication.ui.homeFragment

import android.content.Context
import com.tinhtx.customapplication.dao.DailyExpensesDao
import com.tinhtx.customapplication.dao.ExpenseTypeDao
import com.tinhtx.customapplication.dao.UserDao
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.model.HomeDto
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
    private val expenseTypeDao: ExpenseTypeDao,
    private val context: Context
) {
    private val _updateData = BehaviorSubject.create<DailyExpense>()
    val updateData: Observable<DailyExpense> = _updateData.hide()
    private val _onActionDate = BehaviorSubject.create<Unit>()
    val onActionDate: Observable<Unit> = _onActionDate.hide()

    var dailyExpense: DailyExpense? = null

    var mProduct: String? = null
    var mType: String? = null
    var mDate: String? = null
    var mPrice: String? = null

    fun setupViewData(): List<Group> {
        val listGroup = mutableListOf<Group>()
        val dataUser = getDataUser()
        if (dataUser.isNotEmpty()) {
            listGroup.add(HomeTitleItem("Welcome back ${dataUser.firstOrNull()?.fullName}!"))
        } else {
            listGroup.add(HomeSpaceItem())
            listGroup.add(HomeTitleItem("How are you today?"))
        }
        listGroup.add(
            HomeInputItem(
                HomeDto(
                    homeViewType = HomeViewType.PRODUCT,
                    hint = "Product information",
                    onChangeDone = {
                        mProduct = it.dataProduct
                        updateDailyExpense()
                    }
                )
            )
        )
        listGroup.add(HomeInputItem(
            HomeDto(
                homeViewType = HomeViewType.PRICE,
                hint = "Price information",
                onChangeDone = {
                    mPrice = it.dataPrice
                    updateDailyExpense()
                }
            )
        ))
        listGroup.add(HomeInputItem(
            HomeDto(
                homeViewType = HomeViewType.DATE,
                hint = "Date information",
                onClick = {
                    _onActionDate.onNext(Unit)
                }
            )
        ))
        listGroup.add(HomeDropInputItem(
            HomeDto(
                homeViewType = HomeViewType.TYPE,
                hint = "Type information",
                dataType = getAllType(),
                onChangeDone = {
                    mType = if (it.dataType == "-") null
                    else it.dataType
                    updateDailyExpense()
                }
            ), context = context
        ))
        listGroup.add(HomeButtonItem(
            HomeDto(
                homeViewType = HomeViewType.BUTTON_DONE, title = "Done",
                onClick = {
                    if (it != null) {
                        hideSoftKeyboard(context, it)
                    }
                    dailyExpense?.let { data ->
                        _updateData.onNext(data)
                    }
                }
            )
        ))
        return listGroup
    }

    private fun updateDailyExpense() {
        dailyExpense = DailyExpense(
            type = ExpenseType(type = mType ?: Strings.EMPTY),
            product = mProduct,
            price = mPrice,
            date = mDate,
            location = null
        )
    }

    fun updateDataDate(data: String) {
        mDate = data
        updateDailyExpense()
    }

    fun updateData(user: User) {
        userDao.insertAll(user)
    }

    fun getDataUser(): List<User> {
        return userDao.getAll()
    }

    fun getAllType(): List<ExpenseType> {
        return expenseTypeDao.getAll()
    }

    fun getAllExpenses(): List<DailyExpense> {
        return dailyExpensesDao.getAll()
    }

    fun insertDataExpenses(expense: DailyExpense) {
        dailyExpensesDao.insertAll(expense)
    }
}