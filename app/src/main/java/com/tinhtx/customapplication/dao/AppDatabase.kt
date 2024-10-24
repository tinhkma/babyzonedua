package com.tinhtx.customapplication.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.ExpenseTypeConverter
import com.tinhtx.customapplication.dao.entities.LocationConverter
import com.tinhtx.customapplication.dao.entities.LocationLatLong
import com.tinhtx.customapplication.dao.entities.User
import dagger.Module

@Module
@Database(entities = [User::class, DailyExpense::class, ExpenseType::class, LocationLatLong::class], version = 1)
@TypeConverters(ExpenseTypeConverter::class, LocationConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun typeDao(): ExpenseTypeDao
    abstract fun dailyExpenseDao(): DailyExpensesDao
    abstract fun latLongDao(): LatLongDao
}