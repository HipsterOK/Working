package ru.porcupine.dodgethelightning

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val PREFS_NAME = "kotlin"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }

//    fun saveList(KEY_NAME: String, value: MutableList<String>?) {
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//        editor.putStringSet(KEY_NAME, value?.toSet())
//        editor.apply()
//    }
//
//
//    fun getValueList(KEY_NAME: String): MutableList<String> {
//        return sharedPref.getStringSet(KEY_NAME, setOf<String>())!!.toMutableList()
//    }
}
