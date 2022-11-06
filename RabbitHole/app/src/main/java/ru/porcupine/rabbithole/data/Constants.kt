package ru.porcupine.rabbithole.data

import android.content.Context
import ru.porcupine.rabbithole.R

class Constants(context: Context) {
    val font = R.font.sweet_cheese
    private val screenHeight =
        context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth =
        context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight / 40
    val heightPlayer = screenHeight / 7
    val widthPlayer = heightPlayer / 2
    val sizeGamePos = screenWidth / 5.5
    val sizeHeart = screenWidth/12
}