package ru.porcupine.savetheballoon

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val prefsName = "Save the Balloon"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }

}