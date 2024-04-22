package com.tinhtx.customapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tinhtx.customapplication.dao.entities.ExpenseType

@Dao
interface ExpenseTypeDao {
    @Query("SELECT * FROM expensetype")
    fun getAll(): List<ExpenseType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg types: ExpenseType)

    @Update
    fun updateType(vararg types: ExpenseType)

    @Delete
    fun delete(type: ExpenseType)
}