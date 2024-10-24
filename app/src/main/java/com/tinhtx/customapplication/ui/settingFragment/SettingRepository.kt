package com.tinhtx.customapplication.ui.settingFragment

import android.content.Context
import com.tinhtx.customapplication.base.showToast
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
    var mType: String? = null
    var mPriceLimit: String? = null
    var mName: String? = null
    var settingDto: SettingDto? = null
    private val _onActionDone = BehaviorSubject.create<SettingDto>()
    val onActionDone: Observable<SettingDto> = _onActionDone.hide()

    fun setupViewData(): List<Group> {
        val listGroup = mutableListOf<Group>()
        val dataUser = getDataUser()
        if (dataUser.isNotEmpty()) {
            listGroup.add(HomeTitleItem("Hello ${dataUser.firstOrNull()?.fullName}!"))
        } else {
            listGroup.add(HomeSpaceItem())
            listGroup.add(
                HomeInputItem(
                    HomeDto(homeViewType = HomeViewType.NAME,
                        hint = "Name information",
                        onChangeDone = {
                            mName = it.name
                            updateDataSetting()
                        })
                )
            )
        }
        val hint = if (dataUser.isNotEmpty()) "Type information" else "Please enter Name and Limit!"
        listGroup.add(
            HomeInputItem(
                HomeDto(homeViewType = HomeViewType.TYPE_OTHER,
                    hint = hint,
                    isEnable = dataUser.isNotEmpty(),
                    onChangeDone = {
                        mType = it.otherType
                        updateDataSetting()
                    })
            )
        )
        if (dataUser.firstOrNull()?.limit.isNullOrEmpty()) {
            listGroup.add(
                HomeInputItem(
                    HomeDto(homeViewType = HomeViewType.LIMIT,
                        hint = "Limit information",
                        onChangeDone = {
                            mPriceLimit = it.limit
                            updateDataSetting()
                        })
                )
            )
        } else {
            val limit =
                if (dataUser.firstOrNull()?.limit.isNullOrEmpty()) null else dataUser.firstOrNull()?.limit
            listGroup.add(
                HomeInputItem(
                    HomeDto(homeViewType = HomeViewType.LIMIT,
                        hint = "Limit information",
                        isEnable = dataUser.isEmpty(),
                        title = limit,
                        onChangeDone = {
                            mPriceLimit = it.limit
                            updateDataSetting()
                        })
                )
            )
        }
        listGroup.add(
            HomeButtonItem(
                HomeDto(homeViewType = HomeViewType.BUTTON_DONE, title = "Done", onClick = {
                    if (it != null) {
                        hideSoftKeyboard(context, it)
                    }
                    settingDto?.let { dto -> _onActionDone.onNext(dto) }
                })
            )
        )
        return listGroup
    }

    fun getAllType(): List<ExpenseType> {
        return expenseTypeDao.getAll()
    }

    fun insertType(type: ExpenseType) {
        if (!type.type.isNullOrEmpty()) {
            expenseTypeDao.insertAll(type)
        }
    }

    fun updateLimit(limit: String) {
        mPriceLimit = limit
    }

    fun getDataUser(): List<User> {
        return userDao.getAll()
    }

    fun insertUser(user: User) {
        if (user.fullName != "-")
            userDao.insertAll(user)
    }

    private fun updateDataSetting() {
        settingDto = SettingDto(
            name = mName,
            limit = mPriceLimit,
            type = mType
        )
    }

}