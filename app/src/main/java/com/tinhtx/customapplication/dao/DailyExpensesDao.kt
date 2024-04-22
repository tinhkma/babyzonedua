package com.tinhtx.customapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType

@Dao
interface DailyExpensesDao {
    @Query("SELECT * FROM dailyexpense")
    fun getAll(): List<DailyExpense>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dailyExpense: DailyExpense)

    @Update
    fun updateUsers(vararg dailyExpense: DailyExpense)

    @Delete
    fun delete(dailyExpense: DailyExpense)
}