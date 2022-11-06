package ru.porcupine.mysweets.values

import android.content.Context
import ru.porcupine.mysweets.R

class Constants(context: Context) {
    val font = R.font.barney_pop
    val screenHeight =
        context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth =
        context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val enemySize = screenWidth
    val playerSize = screenWidth/2
    val padding = screenHeight/40
}