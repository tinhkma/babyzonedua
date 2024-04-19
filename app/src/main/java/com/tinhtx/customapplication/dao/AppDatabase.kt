package com.tinhtx.customapplication.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tinhtx.customapplication.model.User
import dagger.Module

@Module
@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var db_instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (db_instance == null) {
                db_instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "baby_database"
                ).allowMainThreadQueries().build()
                db_instance
            }
            return db_instance!!
        }
    }
}