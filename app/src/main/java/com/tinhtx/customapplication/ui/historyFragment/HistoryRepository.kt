package com.tinhtx.customapplication.ui.historyFragment

import com.tinhtx.customapplication.dao.DailyExpensesDao
import com.tinhtx.customapplication.dao.UserDao
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeSpaceItem
import com.tinhtx.customapplication.utils.Strings
import com.xwray.groupie.Group
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(
    private val dailyExpensesDao: DailyExpensesDao,
    private val userDao: UserDao
) {

    fun getAllExpenses(): List<Group> {
        return getListItem(dailyExpensesDao.getAll())
    }

    private fun getListItem(data: List<DailyExpense>): List<Group> {
        val listItem = mutableListOf<Group>()
        listItem.add(HomeSpaceItem())
        val nowAvailable = getDataUser().firstOrNull()?.limit ?: Strings.EMPTY
        val usedAmount = if (data.isNotEmpty()) data.sumOf { it.price?.toDouble() ?: 0.0 } else 0.0
        listItem.add(HistoryHeaderItem(usedAmount.toString(), nowAvailable))
        data.forEach {
            listItem.add(HistoryBillItem(it))
        }
        return listItem
    }

    fun getDataUser(): List<User> {
        return userDao.getAll()
    }
}