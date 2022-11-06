package ru.porcupine.meteoriticrain.other

import android.content.Context

class Constants(context: Context) {
    val screenHeight = context.resources.displayMetrics.heightPixels
    val screenWidth = context.resources.displayMetrics.widthPixels
    val meteorHeight = screenHeight/7
    val meteorWidth = screenWidth/5
}