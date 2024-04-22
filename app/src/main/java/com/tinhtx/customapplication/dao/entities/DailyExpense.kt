package com.tinhtx.customapplication.dao.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class DailyExpense(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @TypeConverters(ExpenseTypeConverter::class) @ColumnInfo(name = "type") val type: ExpenseType?,
    @ColumnInfo(name = "title") val product: String?,
    @ColumnInfo(name = "count") val price: String?,
    @ColumnInfo(name = "date") var date: String?,
    @TypeConverters(LocationConverter::class) @ColumnInfo(name = "location") val location: LocationLatLong?
)