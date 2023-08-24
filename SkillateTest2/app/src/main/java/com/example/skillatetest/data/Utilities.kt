package com.example.skillatetest.data

import android.content.Context
import android.preference.PreferenceManager

object Utilities {

    fun savePrefrence(context: Context, key: String, value: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getPreference(context: Context?, key: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, "")
    }


    fun saveBool(context: Context, key: String, value: Boolean){
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBool(context: Context?, key: String, default: Boolean = false): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(key, default)
    }
}
