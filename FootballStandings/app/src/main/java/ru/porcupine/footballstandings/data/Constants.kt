package ru.porcupine.footballstandings.data

import android.content.Context

class Constants(context: Context) {
//    val font = R.font.aztec
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/40
}