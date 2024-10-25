package com.tinhtx.customapplication.ui.welcomeScreen

import com.tinhtx.customapplication.dao.UserDao
import com.tinhtx.customapplication.dao.entities.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WelcomeRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun insertUser(user: User) {
        if (!user.firstName.isNullOrEmpty() || !user.lastName.isNullOrEmpty())
            userDao.insertAll(user)
    }
}