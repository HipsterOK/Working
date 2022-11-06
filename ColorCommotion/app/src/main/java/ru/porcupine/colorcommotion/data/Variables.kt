package ru.porcupine.colorcommotion.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import ru.porcupine.colorcommotion.utils.SharedPreference

class Variables(context: Context) {
    var gameEnable = true
    var turnId = 0
    var score by mutableStateOf(0)
    var time by mutableStateOf(30)
    var arrayTextColor = mutableStateListOf<String>()
    var arrayColor = mutableStateListOf<Color>()
    var arrayBackgroundColor = mutableStateListOf<Color>()
    var arrayButtonColor = mutableStateListOf<Color>()
    var record by mutableStateOf(SharedPreference(context).getValueInt("record"))
}