package com.tinhtx.customapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "full_name") val fullName: String?
)