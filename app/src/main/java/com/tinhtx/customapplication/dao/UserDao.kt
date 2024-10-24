package com.tinhtx.customapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tinhtx.customapplication.dao.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Update
    fun updateUsers(vararg users: User)

    @Delete
    fun delete(user: User)
}