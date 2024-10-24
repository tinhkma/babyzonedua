package com.tinhtx.customapplication.dao.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "limit") val limit: String?,
    @ColumnInfo(name = "full_name") val fullName: String?
)