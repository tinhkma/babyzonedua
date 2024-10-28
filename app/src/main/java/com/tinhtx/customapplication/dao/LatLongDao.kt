package com.tinhtx.customapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tinhtx.customapplication.dao.entities.LocationLatLong

@Dao
interface LatLongDao {
    @Query("SELECT * FROM locationlatlong")
    fun getAll(): List<LocationLatLong>

    @Query("SELECT * FROM locationlatlong WHERE lat=:lat")
    fun findByLocation(lat: Double): LocationLatLong

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg locationLatLong: LocationLatLong)

    @Update
    fun updateUsers(vararg locationLatLong: LocationLatLong)

    @Delete
    fun delete(locationLatLong: LocationLatLong)
}