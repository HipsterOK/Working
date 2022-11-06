package ru.porcupine.whostolemycheese.values

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.porcupine.whostolemycheese.utils.SharedPreference

class Variables(context: Context) {
    var gameEnable = true
    var score by mutableStateOf(0)

    var record by mutableStateOf(SharedPreference(context).getValueInt("record"))
}