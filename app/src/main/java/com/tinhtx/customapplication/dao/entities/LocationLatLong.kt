package com.tinhtx.customapplication.dao.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationLatLong (
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "lat") val latitude: Double = 0.0,
    @ColumnInfo(name = "long") val longitude: Double = 0.0,
    @ColumnInfo(name = "name") val name: String?,
)