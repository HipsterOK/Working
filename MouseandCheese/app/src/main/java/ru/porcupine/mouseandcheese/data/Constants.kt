package ru.porcupine.mouseandcheese.data

import android.content.Context
import ru.porcupine.mouseandcheese.R

class Constants(context: Context) {
    val font = R.font.comic_shanns
    private val screenHeight =
        context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth =
        context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight / 40
    val heightPlayer = screenHeight / 7
    val widthPlayer = heightPlayer/1.2
    val sizeGamePos = screenWidth / 5.5
    val sizeHeart = screenWidth/12
}