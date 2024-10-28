package com.tinhtx.customapplication.ui.settingFragment

import android.content.Context
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.dao.ExpenseTypeDao
import com.tinhtx.customapplication.dao.UserDao
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.model.HomeDto
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeButtonItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeInputItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeSpaceItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeTitleItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeViewType
import com.tinhtx.customapplication.utils.hideSoftKeyboard
import com.xwray.groupie.Group
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepository @Inject constructor(
    private val expenseTypeDao: ExpenseTypeDao,
    private val userDao: UserDao,
    private val context: Context
) {

    fun getDataUser(): List<User> {
        return userDao.getAll()
    }

    fun insertUser(limit: String) {
        if (limit.isNotEmpty()) {
            val newUser = User(
                uid = getDataUser().firstOrNull()?.uid ?: 0,
                limit = limit,
                firstName = getDataUser().firstOrNull()?.firstName ?: "",
                lastName = getDataUser().firstOrNull()?.lastName ?: ""
            )
            userDao.insertAll(newUser)
        }
    }
}