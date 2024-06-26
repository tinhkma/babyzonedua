package com.tinhtx.customapplication.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(
    private val preferences: SharedPreferences
) {

    fun delete(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return preferences.getBoolean(key, default)
    }

    fun setBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getLong(key: String): Long? {
        return preferences.getLong(key, 0).takeIf { it > 0 }
    }

    fun setLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, "").takeIf { it!!.isNotEmpty() }
    }

    fun setString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }
}