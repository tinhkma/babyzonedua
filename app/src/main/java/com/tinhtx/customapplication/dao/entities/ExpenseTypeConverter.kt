package com.tinhtx.customapplication.dao.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonParseException
import timber.log.Timber

class ExpenseTypeConverter {

    @TypeConverter
    fun stringToObject(data: String): ExpenseType? {
        return if (data.isEmpty()) null
        else try {
            Gson().fromJson(data, ExpenseType::class.java)
        } catch (ex: JsonParseException) {
            Timber.tag("TAG_TEST").d(ex)
            null
        }
    }

    @TypeConverter
    fun objectToString(data: ExpenseType?): String? {
        return if (data == null) null
        else try {
            Gson().toJson(data)
        } catch (ex: JsonParseException) {
            Timber.tag("TAG_TEST").d(ex)
            null
        }
    }
}