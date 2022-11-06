package ru.porcupine.bananamagnate.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val prefsName = "Banana Magnate"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun getValueInt(KEY_NAME: String): Int {
        when (KEY_NAME){
            "bananaPrice" -> return  sharedPref.getInt(KEY_NAME, 1)
            "bananaClick" -> return  sharedPref.getInt(KEY_NAME, 1)
            "update2" -> return  sharedPref.getInt(KEY_NAME, 10)
            "update3" -> return  sharedPref.getInt(KEY_NAME, 15)
            "update4" -> return  sharedPref.getInt(KEY_NAME, 50)
            "update5" -> return  sharedPref.getInt(KEY_NAME, 1000)
        }
        return sharedPref.getInt(KEY_NAME, 0)
    }

}
