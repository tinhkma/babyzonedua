package com.tinhtx.customapplication.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.tinhtx.customapplication.dao.AppDatabase
import com.tinhtx.customapplication.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class AndroidModule {

    @Singleton
    @Provides
    fun getUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    internal fun providePreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}