package ru.porcupine.whostolemycheese.values

import android.content.Context
import ru.porcupine.whostolemycheese.R

class Constants(context: Context) {
    val font = R.font.barney_pop
    val screenHeight =
        context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth =
        context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val enemySize = screenWidth/3
    val playerSize = screenWidth/3
    val padding = screenHeight/40
}