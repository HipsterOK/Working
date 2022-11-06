package ru.porcupine.woodcutterclicker.tools

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val prefsName = "Woodcutter Clicker"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun getValueInt(KEY_NAME: String): Int {
        when (KEY_NAME){
            "oneTap" -> {
                return sharedPref.getInt(KEY_NAME, 1)
            }
            "price0" -> {
                return sharedPref.getInt(KEY_NAME, 10)
            }
            "price1" -> {
                return sharedPref.getInt(KEY_NAME, 10)
            }
            "price2" -> {
                return sharedPref.getInt(KEY_NAME, 100)
            }
            "price3" -> {
                return sharedPref.getInt(KEY_NAME, 200)
            }
            "price4" -> {
                return sharedPref.getInt(KEY_NAME, 1000000)
            }
        }
        return sharedPref.getInt(KEY_NAME, 0)
    }

}
