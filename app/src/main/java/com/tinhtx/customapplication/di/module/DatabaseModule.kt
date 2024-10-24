package com.tinhtx.customapplication.di.module

import android.content.Context
import androidx.room.Room
import com.tinhtx.customapplication.dao.AppDatabase
import com.tinhtx.customapplication.dao.DailyExpensesDao
import com.tinhtx.customapplication.dao.ExpenseTypeDao
import com.tinhtx.customapplication.dao.LatLongDao
import com.tinhtx.customapplication.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Context): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "baby_database.db"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun getUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun getDailyExpenseDao(appDatabase: AppDatabase): DailyExpensesDao {
        return appDatabase.dailyExpenseDao()
    }

    @Singleton
    @Provides
    fun getTypeDao(appDatabase: AppDatabase): ExpenseTypeDao {
        return appDatabase.typeDao()
    }

    @Singleton
    @Provides
    fun getLocationDao(appDatabase: AppDatabase): LatLongDao {
        return appDatabase.latLongDao()
    }
}