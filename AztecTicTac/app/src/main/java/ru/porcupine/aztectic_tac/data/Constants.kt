package ru.porcupine.aztectic_tac.data

import android.content.Context
import ru.porcupine.aztectic_tac.R

class Constants(context: Context) {

    val font = R.font.aztec
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/40
    val crystalHeight = screenHeight/5
    val crystalWidth = screenWidth/3
    private val characterSize = screenHeight/3.8
    var sizeCharacter = listOf(characterSize-screenWidth/10, characterSize+screenWidth/10)
}